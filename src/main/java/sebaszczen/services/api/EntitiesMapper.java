package sebaszczen.services.api;

import org.springframework.stereotype.Repository;
import sebaszczen.model.SynopticData;
import sebaszczen.model.airModel.AirData;

import java.util.List;
import java.util.Map;

@Repository
public interface EntitiesMapper {

    public Map<String, List<SynopticData>> mapCityToSynopticData();
    public Map<String, List<AirData>> mapCityToAirData();
    public List<AirData> injectLocalizationToAirData();
}
