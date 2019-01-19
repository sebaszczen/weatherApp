package sebaszczen.apiProvider;

import sebaszczen.domain.SynopticStation;
import sebaszczen.domain.gios.dto.AirConditionDataDto;
import sebaszczen.domain.gios.dto.StationLocalizationDto;

import java.util.List;

public interface ApiProvider {
    List<SynopticStation.SynopticStationDto> getAllSynopticStationDto();

    SynopticStation.SynopticStationDto getSynopticDataByStationName(String cityName);

    List<StationLocalizationDto> getStationLocalizationDto();

    List<AirConditionDataDto> getAllAirConditionDataDto();

    AirConditionDataDto getAirConditionDataByStationIndex(int index);
}
