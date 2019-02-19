package sebaszczen.services.api;

import sebaszczen.model.SynopticData;
import sebaszczen.model.airModel.AirData;

import java.util.List;
import java.util.Map;

public interface EntitiesMapper {

    public Map<String, List<SynopticData>> mapCityToSynopticData();
    public Map<String, List<AirData>> mapCityToAirData();
    public List<AirData> injectLocalizationToAirData();
}
