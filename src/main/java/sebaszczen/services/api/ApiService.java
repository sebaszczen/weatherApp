package sebaszczen.services.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sebaszczen.apiProvider.ApiProvider;
import sebaszczen.model.SynopticStation;
import sebaszczen.model.AirConditionData;
import sebaszczen.model.StationLocalization;
import sebaszczen.dto.AirConditionDataDto;
import sebaszczen.repository.AirConditionDataRepository;
import sebaszczen.repository.ImgwApiRepository;
import sebaszczen.repository.StationLocalizationRepository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ApiService implements IApiService {

    private final Logger logger = Logger.getLogger(String.valueOf(ApiService.class));

    private final ImgwApiRepository imgwApiRepository;

    private final ApiProvider apiProvider;

    private final StationLocalizationRepository stationLocalizationRepository;

    private final AirConditionDataRepository giosApiRepository;

    @Autowired
    public ApiService(ImgwApiRepository imgwApiRepository, ApiProvider apiProvider, StationLocalizationRepository stationLocalizationRepository, AirConditionDataRepository giosApiRepository) {
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

        if (imgwApiIsUpToDate()) {
            List<SynopticStation> synopticStationList = apiProvider.getAllSynopticStation();
            synopticStationList.forEach(imgwApiRepository::save);
        }

        if (giosApiIsUpToDate()){
            List<StationLocalization> stationLocalizationList = apiProvider.getStationLocalization();
        stationLocalizationList.forEach(stationLocalizationRepository::save);

        List<AirConditionData> airConditionDataList = apiProvider.getAllAirConditionData();
        airConditionDataList.forEach(giosApiRepository::save);
    }
}

    private boolean imgwApiIsUpToDate() {
        String stationNames[]={"warszawa","chojnice","hel","katowice","kielce"};
        for (String stationName : stationNames) {
            Optional<SynopticStation> station = apiProvider.getSynopticDataByStationName(stationName);
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
        int[] stationIndex = {11114, 115, 116, 117};
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
