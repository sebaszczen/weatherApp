package sebaszczen.apiProvider;

import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import sebaszczen.model.AirConditionData;
import sebaszczen.model.StationLocalization;
import sebaszczen.model.SynopticStation;

import java.util.List;
import java.util.Optional;

public interface ApiProvider {
    List<SynopticStation> getAllSynopticStation();

    Optional<SynopticStation> getSynopticDataByStationName(String cityName) throws ResourceAccessException;

    List<StationLocalization> getStationLocalization();

    List<AirConditionData> getAllAirConditionData();

    Optional<AirConditionData> getAirConditionDataByStationIndex(int index);
}
