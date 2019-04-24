package sebaszczen.apiProvider;

import org.springframework.web.client.ResourceAccessException;
import sebaszczen.model.airModel.AirData;
import sebaszczen.model.airModel.AirMeasurementLocalization;
import sebaszczen.model.synopticModel.SynopticData;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ApiProvider {
    List<SynopticData> getAllSynopticStation();

    Optional<SynopticData> getSynopticDataByStationName(String cityName) throws ResourceAccessException;

    Map<Integer, AirMeasurementLocalization> getStationLocalization();

    List<AirData> getAirData();

    Optional<AirData> getAirConditionDataByStationIndex(int index);
}
