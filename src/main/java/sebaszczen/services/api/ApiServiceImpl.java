package sebaszczen.services.api;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;
import sebaszczen.apiProvider.ApiProvider;
import sebaszczen.model.airModel.AirData;
import sebaszczen.model.airModel.AirQuality;
import sebaszczen.model.SynopticData;
import sebaszczen.model.airModel.AirMeasurementLocalization;
import sebaszczen.repository.AirConditionDataRepository;
import sebaszczen.repository.SynopticDataRepository;
import sebaszczen.repository.LevelRepository;
import sebaszczen.repository.StationLocalizationRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ApiServiceImpl implements ApiService {

    org.apache.logging.log4j.Logger logger = LogManager.getLogger(ApiServiceImpl.class);

    private final SynopticDataRepository synopticDataRepository;

    private final ApiProvider apiProvider;

    private final StationLocalizationRepository stationLocalizationRepository;

    private final AirConditionDataRepository giosApiRepository;

    private final LevelRepository levelRepository;

    @Autowired
    public ApiServiceImpl(SynopticDataRepository synopticDataRepository, ApiProvider apiProvider, StationLocalizationRepository stationLocalizationRepository, AirConditionDataRepository giosApiRepository, LevelRepository levelRepository) {
        this.synopticDataRepository = synopticDataRepository;
        this.apiProvider = apiProvider;
        this.stationLocalizationRepository = stationLocalizationRepository;
        this.giosApiRepository = giosApiRepository;
        this.levelRepository = levelRepository;
    }

    @Override
    @Transactional
    public void saveImgwData() {
        List<SynopticData> synopticDataList = apiProvider.getAllSynopticStation();
        synopticDataList.forEach(synopticDataRepository::save);
    }

    @Override
    @Transactional
    @Scheduled(fixedRate = 3600000)
    public void saveData() {

        try {
            if (imgwApiIsUpToDate()) {
                List<SynopticData> synopticDataList = apiProvider.getAllSynopticStation();
                synopticDataList.forEach(synopticDataRepository::save);
            }

            if (giosApiIsUpToDate()) {
                List<AirMeasurementLocalization> airMeasurementLocalizationList = apiProvider.getStationLocalization();
                airMeasurementLocalizationList.forEach(stationLocalizationRepository::save);

                List<AirData> airDataList = apiProvider.getAllAirConditionData();
                airDataList.forEach(airConditionData -> {
                    AirQuality[] airQualities = {airConditionData.getC6H6IndexAirQuality(),airConditionData.getCoIndexAirQuality()
                    ,airConditionData.getNo2IndexAirQuality(),airConditionData.getO3IndexAirQuality(),airConditionData.getPm10IndexAirQuality()
                    ,airConditionData.getPm25IndexAirQuality(),airConditionData.getSo2IndexAirQuality(),airConditionData.getStIndexAirQuality()};
                    Arrays.stream(airQualities).filter(airQuality -> airQuality !=null&& airQuality.getId()!=null).forEach(levelRepository::save);
//                    levelRepository.save(airConditionData.getC6H6IndexAirQuality());
//                    levelRepository.save(airConditionData.getCoIndexAirQuality());
//                    levelRepository.save(airConditionData.getNo2IndexAirQuality());
//                    levelRepository.save(airConditionData.getO3IndexAirQuality());
//                    levelRepository.save(airConditionData.getPm10IndexAirQuality());
//                    levelRepository.save(airConditionData.getPm25IndexAirQuality());
//                    levelRepository.save(airConditionData.getSo2IndexAirQuality());
//                    levelRepository.save(airConditionData.getStIndexAirQuality());

                        giosApiRepository.save(airConditionData);
                });
            }
        }
        catch (ResourceAccessException e)
        {
            logger.error("couldnt connect with external api "+e);
        }

}

    private boolean imgwApiIsUpToDate()throws ResourceAccessException {
        String stationNames[]={"warszawa","chojnice","hel","katowice","kielce"};
        for (String stationName : stationNames) {
            Optional<SynopticData> station;
                station = apiProvider.getSynopticDataByStationName(stationName);
                if (station.isPresent()) {
                    SynopticData synopticData = station.get();
                    int hour = synopticData.getLocalDateTime().getHour();
                    int dayOfMonth = synopticData.getLocalDateTime().getDayOfMonth();
                    synopticDataRepository.findAll();
                    return synopticDataRepository.checkIfContain(hour, dayOfMonth) == 0;
                }
        }
        return false;
    }

    private boolean giosApiIsUpToDate() {
        int[] stationIndex = {114, 115, 116, 117};
        for (int index : stationIndex) {
            Optional<AirData> airConditionDataByStationIndex = apiProvider.getAirConditionDataByStationIndex(index);
            if (airConditionDataByStationIndex.isPresent()) {
                AirData airData = airConditionDataByStationIndex.get();
                int hour1 = airData.getStCalcDate().getHour();
                int dayOfMonth1 = airData.getStCalcDate().getDayOfMonth();
                return giosApiRepository.checkIfContain(hour1, dayOfMonth1) == 0;
            }
        }
        return false;
    }
}
