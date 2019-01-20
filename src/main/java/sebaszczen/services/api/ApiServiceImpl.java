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
        List<SynopticStation> synopticStationList = apiProvider.getAllSynopticStationDto()
                .parallelStream().map(SynopticStation::new).collect(Collectors.toList());
        synopticStationList.forEach(imgwApiRepository::save);
    }

    @Override
    @Transactional
    @Async
    @Scheduled(fixedRate = 3600000)
    public void saveData() {

        if (imgwApiIsUpToDate()) {
            List<SynopticStation> synopticStationList = apiProvider.getAllSynopticStationDto()
                    .parallelStream().map(SynopticStation::new).collect(Collectors.toList());
            synopticStationList.forEach(imgwApiRepository::save);
        }

        if (giosApiIsUpToDate()){
            List<StationLocalization> stationLocalizationList = apiProvider.getStationLocalizationDto()
                    .parallelStream().map(StationLocalization::new).collect(Collectors.toList());
        stationLocalizationList.forEach(stationLocalizationRepository::save);

        List<AirConditionData> airConditionDataList = apiProvider.getAllAirConditionDataDto().parallelStream()
                .map(AirConditionData::new).collect(Collectors.toList());
        airConditionDataList.forEach(giosApiRepository::save);
    }
}

    private boolean imgwApiIsUpToDate() {
        SynopticStation.SynopticStationDto warszawa = apiProvider.getSynopticDataByStationName("warszawa");
        int hour = warszawa.getGodzina_pomiaru().getHour();
        int dayOfMonth = warszawa.getData_pomiaru().getDayOfMonth();
        imgwApiRepository.findAll();
        return imgwApiRepository.checkIfContain(hour, dayOfMonth) == 0;
    }

    private boolean giosApiIsUpToDate() {
        AirConditionDataDto airConditionDataDto = apiProvider.getAirConditionDataByStationIndex(114);
        int hour1 = airConditionDataDto.getStCalcDate().getHour();
        int dayOfMonth1 = airConditionDataDto.getStCalcDate().getDayOfMonth();
        return giosApiRepository.checkIfContain(hour1, dayOfMonth1)==0;
    }


}
