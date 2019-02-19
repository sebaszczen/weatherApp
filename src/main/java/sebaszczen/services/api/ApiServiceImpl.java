package sebaszczen.services.api;

import org.apache.logging.log4j.LogManager;
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
    public ApiServiceImpl(SynopticDataRepository synopticDataRepository, ApiProvider apiProvider,  AirDataRepository airDataRepository, AirQualityRepository airQualityRepository, CityRepository cityRepository, EntitiesMapper entitiesMapper) {
        this.synopticDataRepository = synopticDataRepository;
        this.apiProvider = apiProvider;
        this.airDataRepository = airDataRepository;
        this.airQualityRepository = airQualityRepository;
        this.cityRepository = cityRepository;
        this.entitiesMapper = entitiesMapper;
    }

    @Override
    @Transactional
    @Scheduled(fixedRate = 3600000)
    public void saveData() {
        Map<String, List<SynopticData>> cityToSynopticData = entitiesMapper.mapCityToSynopticData();
        Map<String, List<AirData>> cityToAirData = entitiesMapper.mapCityToAirData();
        try {
            if (synopticDataIsNotUpToDate()) {
                updateCitySynopticData(cityToSynopticData);
            }
            if (airDataIsNotUpToDate()) {
                updateCityAirData(cityToAirData);
            }
        }
        catch (ResourceAccessException e)
        {
            logger.error("couldnt connect with external api "+e);
        }
}

    private void updateCityAirData(Map<String, List<AirData>> cityToAirData) {
        saveAirQualityData();
        for (String cityName : cityToAirData.keySet()) {
            List<AirData> value = cityToAirData.get(cityName);
                if (cityRepository.existsAllByName(cityName)) {
                    City cityByName = cityRepository.findCityByName(cityName);
                    List<AirData> airDataList1 = cityByName.getAirDataList();
                    if (airDataList1!=null) {
                        airDataList1.addAll(value);
                    }
                    else {
                        cityByName.setAirDataList(value);
                    }
                    cityRepository.save(cityByName);
                } else {
                    cityRepository.save(new City(cityName, null, value));
                }
        }
    }

    private void saveAirQualityData() {
        List<AirData> airDataList = entitiesMapper.injectLocalizationToAirData();
        airDataList.forEach(airData -> {
            AirQuality[] airQualities = {airData.getC6H6IndexAirQuality(),airData.getCoIndexAirQuality()
            ,airData.getNo2IndexAirQuality(),airData.getO3IndexAirQuality(),airData.getPm10IndexAirQuality()
            ,airData.getPm25IndexAirQuality(),airData.getSo2IndexAirQuality(),airData.getStIndexAirQuality()};
            Arrays.stream(airQualities).filter(airQuality -> airQuality !=null&& airQuality.getId()!=null).forEach(airQualityRepository::save);
        });
    }

    private void updateCitySynopticData(Map<String, List<SynopticData>> cityToSynopticData) {
        for (String cityName : cityToSynopticData.keySet()) {
            List<SynopticData> synopticDataList = cityToSynopticData.get(cityName);
            if (cityRepository.existsAllByName(cityName)){
                City cityByName = cityRepository.findCityByName(cityName);
                cityByName.getSynopticDataList().addAll(synopticDataList);
                cityRepository.save(cityByName);
            }
            else {
                cityRepository.save(new City(cityName, synopticDataList, null));
            }
        }
    }

    private boolean synopticDataIsNotUpToDate()throws ResourceAccessException {
        String stationNames[]={"warszawa","chojnice","hel","katowice","kielce"};
        for (String stationName : stationNames) {
            Optional<SynopticData> station;
                station = apiProvider.getSynopticDataByStationName(stationName);
                if (station.isPresent()) {
                    SynopticData synopticData = station.get();
                    int hour = synopticData.getLocalDateTime().getHour();
                    int dayOfMonth = synopticData.getLocalDateTime().getDayOfMonth();
                    return synopticDataRepository.contain(hour, dayOfMonth) == 0;
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
                return airDataRepository.contain(hour1, dayOfMonth1) == 0;
            }
        }
        return false;
    }
}
