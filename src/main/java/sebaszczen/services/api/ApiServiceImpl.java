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
import sebaszczen.repository.AirConditionDataRepository;
import sebaszczen.repository.ImgwApiRepository;
import sebaszczen.repository.StationLocalizationRepository;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ApiServiceImpl implements ApiService {

    private Logger logger = Logger.getLogger(String.valueOf(ApiServiceImpl.class));

    @Autowired
    private ImgwApiRepository imgwApiRepository;

    @Autowired
    private ApiProvider apiProvider;

    @Autowired
    private StationLocalizationRepository stationLocalizationRepository;

    @Autowired
    private AirConditionDataRepository airConditionDataRepository;

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
    @Scheduled(fixedRate =60000)
    public void saveData() {
        String name = Thread.currentThread().getName();
        logger.info("watek:  "+ name);

        List<SynopticStation> synopticStationList = apiProvider.getAllSynopticStationDto()
                .parallelStream().map(SynopticStation::new).collect(Collectors.toList());
        synopticStationList.forEach(imgwApiRepository::save);

        List<StationLocalization> stationLocalizationList = apiProvider.getStationLocalizationDto()
                .parallelStream().map(StationLocalization::new).collect(Collectors.toList());
        stationLocalizationList.forEach(stationLocalizationRepository::save);

        List<AirConditionData> airConditionDataList = apiProvider.getAirConditionData().parallelStream()
                .map(AirConditionData::new).collect(Collectors.toList());
        airConditionDataList.forEach(airConditionDataRepository::save);
    }


}
