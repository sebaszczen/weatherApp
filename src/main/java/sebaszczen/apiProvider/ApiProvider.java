package sebaszczen.apiProvider;

import sebaszczen.model.AirConditionData;
import sebaszczen.model.StationLocalization;
import sebaszczen.model.SynopticStation;

import java.util.List;

public interface ApiProvider {
    List<SynopticStation> getAllSynopticStation();

    SynopticStation getSynopticDataByStationName(String cityName);

    List<StationLocalization> getStationLocalization();

    List<AirConditionData> getAllAirConditionData();

    AirConditionData getAirConditionDataByStationIndex(int index);
}
