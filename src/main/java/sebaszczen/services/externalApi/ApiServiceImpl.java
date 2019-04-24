package sebaszczen.services.externalApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;
import sebaszczen.apiProvider.ApiProvider;
import sebaszczen.model.cityModel.City;
import sebaszczen.model.airModel.AirData;
import sebaszczen.model.airModel.AirQuality;
import sebaszczen.model.synopticModel.SynopticData;
import sebaszczen.repository.*;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public class ApiServiceImpl implements ApiService {

    Logger logger = LoggerFactory.getLogger(ApiServiceImpl.class);

    private final SynopticDataRepository synopticDataRepository;

    private final AirDataRepository airDataRepository;

    private final ApiProvider apiProvider;

    private final AirQualityRepository airQualityRepository;

    private final CityRepository cityRepository;

    private final EntitiesMapper entitiesMapper;

    @Autowired
    public ApiServiceImpl(SynopticDataRepository synopticDataRepository, ApiProvider apiProvider, AirDataRepository airDataRepository, AirQualityRepository airQualityRepository, CityRepository cityRepository, EntitiesMapper entitiesMapper) {
        this.synopticDataRepository = synopticDataRepository;
        this.apiProvider = apiProvider;
        this.airDataRepository = airDataRepository;
        this.airQualityRepository = airQualityRepository;
        this.cityRepository = cityRepository;
        this.entitiesMapper = entitiesMapper;
    }

    @Override
    @Scheduled(fixedRate = 3600000)
    public void saveData() {
        Future<Map<String, List<SynopticData>>> cityToSynopticData = entitiesMapper.mapCityToSynopticData();
        Future<Map<String, List<AirData>>> cityToAirData = entitiesMapper.mapCityToAirData();

        try {
            if (synopticDataIsNotUpToDate()) {
                try {
                    updateCitySynopticData(cityToSynopticData.get());
//                    throw new InterruptedException();
                } catch (InterruptedException | ExecutionException e) {
                    logger.warn("Error occured during stopping thread", e);
                }
            }
        } catch (ResourceAccessException e) {
            logger.warn("Error occured during updating synoptic data", e);
        }
        try {
            if (airDataIsNotUpToDate()) {
                try {
                    saveAirQualityData(cityToAirData);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    updateCityAirData(cityToAirData.get());
                } catch (InterruptedException | ExecutionException e) {
                    logger.warn("Error occured during stopping thread", e);
                }
            }
        } catch (ResourceAccessException e) {
            logger.warn("Error occured during updating air data", e);
        }
    }

    @Transactional
    private void updateCityAirData(Map<String, List<AirData>> cityToAirData) {
        for (String cityName : cityToAirData.keySet()) {
            List<AirData> airDataList = cityToAirData.get(cityName);
            if (cityRepository.existsAllByName(cityName)) {
                City cityWithAirData = cityRepository.findCityWithAirData(cityName);
                cityRepository.save(cityWithAirData);
                cityWithAirData.addAirData(airDataList);
                cityRepository.save(cityWithAirData);
            } else {
                cityRepository.save(new City(cityName, null, airDataList));
            }
        }
    }

    private void saveAirQualityData(Future<Map<String, List<AirData>>> cityToAirData) throws ExecutionException, InterruptedException {
        cityToAirData.get().values().forEach(airDataList1 -> airDataList1.forEach(airData -> {
            AirQuality[] airQualities = {airData.getC6H6IndexLevel(), airData.getCoIndexLevel()
                    , airData.getNo2IndexLevel(), airData.getO3IndexLevel(), airData.getPm10IndexLevel()
                    , airData.getPm25IndexLevel(), airData.getSo2IndexLevel(), airData.getStIndexLevel()};
            Arrays.stream(airQualities).filter(airQuality -> airQuality != null && airQuality.getId() != null && !airQuality.isSaved()).
                    forEach(airQuality -> {
                        airQualityRepository.save(airQuality);
                        airQuality.setSaved(true);
                    });
        }));
    }

    @Transactional
    private void updateCitySynopticData(Map<String, List<SynopticData>> cityToSynopticData) {
        for (String cityName : cityToSynopticData.keySet()) {
            List<SynopticData> synopticDataList = cityToSynopticData.get(cityName);
            if (cityRepository.existsAllByName(cityName)) {
                City cityByName = cityRepository.findCityByName(cityName);
                cityByName.addSynopticData(synopticDataList);
                cityRepository.save(cityByName);
            } else {
                cityRepository.save(new City(cityName, synopticDataList, null));
            }
        }
    }

    private boolean synopticDataIsNotUpToDate() throws ResourceAccessException {
        String stationNames[] = {"warszawa", "chojnice", "hel", "katowice", "kielce"};
        for (String stationName : stationNames) {
            Optional<SynopticData> station;
            station = apiProvider.getSynopticDataByStationName(stationName);
            if (station.isPresent()) {
                SynopticData synopticData = station.get();
                int hour = synopticData.getLocalDateTime().getHour();
                int dayOfMonth = synopticData.getLocalDateTime().getDayOfMonth();
                int month = synopticData.getLocalDateTime().getMonth().getValue();
                int year = synopticData.getLocalDateTime().getYear();
                return synopticDataRepository.contain(hour, dayOfMonth, month, year) == 0;
            }
        }
        return false;
    }

    private boolean airDataIsNotUpToDate() {
        List<Integer> stationIndex = apiProvider.getAirData().subList(0,4).parallelStream().map(x -> x.getStationId()).collect(Collectors.toList());
        for (int index : stationIndex) {
            Optional<AirData> airConditionDataByStationIndex = apiProvider.getAirConditionDataByStationIndex(index);
            if (airConditionDataByStationIndex.isPresent()) {
                AirData airData = airConditionDataByStationIndex.get();
                int hour1 = airData.getStCalcDate().getHour();
                int dayOfMonth1 = airData.getStCalcDate().getDayOfMonth();
                int month = airData.getStCalcDate().getMonth().getValue();
                int year = airData.getStCalcDate().getYear();
                return airDataRepository.contain(hour1, dayOfMonth1, month, year) == 0;
            }
        }
        return false;
    }
}
