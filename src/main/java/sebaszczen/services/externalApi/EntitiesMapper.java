package sebaszczen.services.externalApi;

import sebaszczen.model.airModel.AirData;
import sebaszczen.model.synopticModel.SynopticData;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

public interface EntitiesMapper {

    public Future<Map<String, List<SynopticData>>> mapCityToSynopticData();
    public Future<Map<String, List<AirData>>> mapCityToAirData();
    public List<AirData> injectLocalizationToAirData();
}
