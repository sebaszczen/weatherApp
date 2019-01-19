package sebaszczen.services.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sebaszczen.apiProvider.ApiProvider;
import sebaszczen.domain.SynopticStation;
import sebaszczen.domain.gios.AirConditionData;
import sebaszczen.domain.gios.StationLocalization;
import sebaszczen.domain.gios.dto.AirConditionDataDto;
import sebaszczen.repository.AirConditionDataRepository;
import sebaszczen.repository.ImgwApiRepository;
import sebaszczen.repository.StationLocalizationRepository;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ApiServiceImpl implements ApiService {

    private final Logger logger = Logger.getLogger(String.valueOf(ApiServiceImpl.class));

    private final ImgwApiRepository imgwApiRepository;

    private final ApiProvider apiProvider;

    private final StationLocalizationRepository stationLocalizationRepository;

    private final AirConditionDataRepository airConditionDataRepository;

    @Autowired
    public ApiServiceImpl(ImgwApiRepository imgwApiRepository, ApiProvider apiProvider, StationLocalizationRepository stationLocalizationRepository, AirConditionDataRepository airConditionDataRepository) {
        this.imgwApiRepository = imgwApiRepository;
        this.apiProvider = apiProvider;
        this.stationLocalizationRepository = stationLocalizationRepository;
        this.airConditionDataRepository = airConditionDataRepository;
    }

    @Override
    @Transactional
    public void saveImgwData() {
        List<SynopticStation> synopticStationList = apiProvider.getAllSynopticStationDto()
                .parallelStream().map(SynopticStation::new).collect(Collectors.toList());
        synopticStationList.forEach(imgwApiRepository::save);
    }

    @Override
    @Transactional
    @Async
    @Scheduled(fixedRate = 3600000)
    public void saveData() {
        SynopticStation.SynopticStationDto warszawa = apiProvider.getSynopticDataByStationName("warszawa");
        if (imgwApiRepository.checkIfContain
                (warszawa.getGodzina_pomiaru().getHour(), warszawa.getData_pomiaru().getDayOfMonth()) == 0) {
            List<SynopticStation> synopticStationList = apiProvider.getAllSynopticStationDto()
                    .parallelStream().map(SynopticStation::new).collect(Collectors.toList());
            synopticStationList.forEach(imgwApiRepository::save);
        }

        AirConditionDataDto airConditionDataDto = apiProvider.getAirConditionDataByIndex(114);
        if (airConditionDataRepository.checkIfContain(airConditionDataDto.getStCalcDate().getHour(),
                airConditionDataDto.getStCalcDate().getDayOfMonth())==0){
            List<StationLocalization> stationLocalizationList = apiProvider.getStationLocalizationDto()
                    .parallelStream().map(StationLocalization::new).collect(Collectors.toList());
        stationLocalizationList.forEach(stationLocalizationRepository::save);

        List<AirConditionData> airConditionDataList = apiProvider.getAllAirConditionDataDto().parallelStream()
                .map(AirConditionData::new).collect(Collectors.toList());
        airConditionDataList.forEach(airConditionDataRepository::save);
    }
}


}
