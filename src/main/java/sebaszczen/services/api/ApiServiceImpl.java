package sebaszczen.services.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sebaszczen.apiProvider.ApiProvider;
import sebaszczen.domain.SynopticStation;
import sebaszczen.domain.gios.StationLocalization;
import sebaszczen.domain.gios.dto.StationLocalizationDto;
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
    StationLocalizationRepository stationLocalizationRepository;

    @Override
    public void saveImgwData() {
        List<SynopticStation> synopticStationList = apiProvider.getAllSynopticData()
                .parallelStream().map(SynopticStation::new).collect(Collectors.toList());

        synopticStationList.forEach(imgwApiRepository::save);
    }

    @Override
    public void saveData() {
        List<StationLocalization> stationLocalizationList = apiProvider.getStationLocalizationsFromGiosApi()
                .parallelStream().map(StationLocalization::new).collect(Collectors.toList());
        stationLocalizationList.forEach(stationLocalizationRepository::save);
    }


}
