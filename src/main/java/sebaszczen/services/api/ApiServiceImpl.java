package sebaszczen.services.api;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;
import sebaszczen.apiProvider.ApiProvider;
import sebaszczen.model.SynopticStation;
import sebaszczen.model.AirConditionData;
import sebaszczen.model.StationLocalization;
import sebaszczen.repository.AirConditionDataRepository;
import sebaszczen.repository.ImgwApiRepository;
import sebaszczen.repository.StationLocalizationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ApiServiceImpl implements ApiService {

    org.apache.logging.log4j.Logger logger = LogManager.getLogger(ApiServiceImpl.class);

    private final ImgwApiRepository imgwApiRepository;

    private final ApiProvider apiProvider;

    private final StationLocalizationRepository stationLocalizationRepository;

    private final AirConditionDataRepository giosApiRepository;

    @Autowired
    public ApiServiceImpl(ImgwApiRepository imgwApiRepository, ApiProvider apiProvider, StationLocalizationRepository stationLocalizationRepository, AirConditionDataRepository giosApiRepository) {
        this.imgwApiRepository = imgwApiRepository;
        this.apiProvider = apiProvider;
        this.stationLocalizationRepository = stationLocalizationRepository;
        this.giosApiRepository = giosApiRepository;
    }

    @Override
    @Transactional
    public void saveImgwData() {
        List<SynopticStation> synopticStationList = apiProvider.getAllSynopticStation();
        synopticStationList.forEach(imgwApiRepository::save);
    }

    @Override
    @Transactional
    @Scheduled(fixedRate = 3600000)
    public void saveData() {

        try {
            if (imgwApiIsUpToDate()) {
                List<SynopticStation> synopticStationList = apiProvider.getAllSynopticStation();
                synopticStationList.forEach(imgwApiRepository::save);
            }

            if (giosApiIsUpToDate()) {
                List<StationLocalization> stationLocalizationList = apiProvider.getStationLocalization();
                stationLocalizationList.forEach(stationLocalizationRepository::save);

                List<AirConditionData> airConditionDataList = apiProvider.getAllAirConditionData();
                airConditionDataList.forEach(giosApiRepository::save);
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
            Optional<SynopticStation> station = null;
                station = apiProvider.getSynopticDataByStationName(stationName);
                if (station.isPresent()) {
                    SynopticStation synopticStation = station.get();
                    int hour = synopticStation.getLocalDateTime().getHour();
                    int dayOfMonth = synopticStation.getLocalDateTime().getDayOfMonth();
                    imgwApiRepository.findAll();
                    return imgwApiRepository.checkIfContain(hour, dayOfMonth) == 0;
                }
        }
        return false;
    }

    private boolean giosApiIsUpToDate() {
        int[] stationIndex = {114, 115, 116, 117};
        for (int index : stationIndex) {
            Optional<AirConditionData> airConditionDataByStationIndex = apiProvider.getAirConditionDataByStationIndex(index);
            if (airConditionDataByStationIndex.isPresent()) {
                AirConditionData airConditionData = airConditionDataByStationIndex.get();
                int hour1 = airConditionData.getStCalcDate().getHour();
                int dayOfMonth1 = airConditionData.getStCalcDate().getDayOfMonth();
                return giosApiRepository.checkIfContain(hour1, dayOfMonth1) == 0;
            }
        }
        return false;
    }
}