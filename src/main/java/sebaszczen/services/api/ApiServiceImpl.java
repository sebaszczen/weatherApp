package sebaszczen.services.api;

import org.apache.logging.log4j.LogManager;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;
import sebaszczen.apiProvider.ApiProvider;
import sebaszczen.model.City;
import sebaszczen.model.airModel.AirData;
import sebaszczen.model.airModel.AirQuality;
import sebaszczen.model.SynopticData;
import sebaszczen.repository.*;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class ApiServiceImpl implements ApiService {

    org.apache.logging.log4j.Logger logger = LogManager.getLogger(ApiServiceImpl.class);

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
        System.out.println("tutaj" + Thread.currentThread().getName());
        Future<Map<String, List<SynopticData>>> cityToSynopticData = entitiesMapper.mapCityToSynopticData();
        Future<Map<String, List<AirData>>> cityToAirData = entitiesMapper.mapCityToAirData();

        try {
            if (synopticDataIsNotUpToDate()) {
                try {
                    try {
                        updateCitySynopticData(cityToSynopticData.get(5, TimeUnit.MINUTES));
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            if (airDataIsNotUpToDate()) {
                saveAirQualityData(cityToAirData);
                updateCityAirData(cityToAirData.get());
            }
        } catch (ResourceAccessException e) {
            logger.error("couldnt connect with external api " + e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    private void updateCityAirData(Map<String, List<AirData>> cityToAirData) {
        for (String cityName : cityToAirData.keySet()) {
            List<AirData> value = cityToAirData.get(cityName);
            if (cityRepository.existsAllByName(cityName)) {
//                City cityWithAirData = cityRepository.findCityWithAirData(cityName);
                City cityWithAirData = cityRepository.findCityWithAirData(cityName);
                 cityRepository.findCityWithAirData2(cityName);
                cityWithAirData.addAirData(value);
                cityRepository.save(cityWithAirData);
            } else {
                cityRepository.save(new City(cityName, null, value));
            }
        }
    }

    private void saveAirQualityData(Future<Map<String, List<AirData>>> cityToAirData) throws ExecutionException, InterruptedException {

        cityToAirData.get().values().forEach(airDataList1 ->airDataList1.forEach(airData -> {
            AirQuality[] airQualities = {airData.getC6H6IndexAirQuality(), airData.getCoIndexAirQuality()
                    , airData.getNo2IndexAirQuality(), airData.getO3IndexAirQuality(), airData.getPm10IndexAirQuality()
                    , airData.getPm25IndexAirQuality(), airData.getSo2IndexAirQuality(), airData.getStIndexAirQuality()};
            Arrays.stream(airQualities).filter(airQuality -> airQuality != null && airQuality.getId() != null && !airQuality.isSaved()).
                    forEach(airQuality->{airQualityRepository.save(airQuality);
                        airQuality.setSaved(true);
                    });
        })) ;
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
        int[] stationIndex = {114, 115, 116, 117};
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
