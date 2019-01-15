package sebaszczen.services.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sebaszczen.apiProvider.ApiProvider;
import sebaszczen.domain.SynopticStation;
import sebaszczen.domain.gios.AirConditionData;
import sebaszczen.domain.gios.StationLocalization;
import sebaszczen.domain.gios.dto.StationLocalizationDto;
import sebaszczen.repository.AirConditionDataRepository;
import sebaszczen.repository.ImgwApiRepository;
import sebaszczen.repository.StationLocalizationRepository;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ImgwApiRepository imgwApiRepository;

    @Autowired
    private ApiProvider apiProvider;

    @Autowired
    private StationLocalizationRepository stationLocalizationRepository;

    @Autowired
    private AirConditionDataRepository airConditionDataRepository;

    @Override
    public void saveImgwData() {
        List<SynopticStation> synopticStationList = apiProvider.getAllSynopticData()
                .parallelStream().map(SynopticStation::new).collect(Collectors.toList());
        synopticStationList.forEach(imgwApiRepository::save);
    }

    @Override
    public void saveData() {
        List<SynopticStation> synopticStationList = apiProvider.getAllSynopticData()
                .parallelStream().map(SynopticStation::new).collect(Collectors.toList());
        synopticStationList.forEach(imgwApiRepository::save);

        List<StationLocalization> stationLocalizationList = apiProvider.getStationLocalizationsFromGiosApi()
                .parallelStream().map(StationLocalization::new).collect(Collectors.toList());
        stationLocalizationList.forEach(stationLocalizationRepository::save);

        List<AirConditionData> airConditionDataList = apiProvider.getAirConditionData().parallelStream()
                .map(AirConditionData::new).collect(Collectors.toList());
        airConditionDataList.forEach(airConditionDataRepository::save);
    }


}
