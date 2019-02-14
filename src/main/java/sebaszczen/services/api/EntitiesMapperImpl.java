package sebaszczen.services.api;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sebaszczen.apiProvider.ApiProvider;
import sebaszczen.model.SynopticData;
import sebaszczen.model.airModel.AirData;
import sebaszczen.model.airModel.AirMeasurementLocalization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EntitiesMapperImpl implements EntitiesMapper {

    @Autowired
    private ApiProvider apiProvider;

    @Override
    public Map<String, List<SynopticData>> mapCityToSynopticData() {
        Map<String, List<SynopticData>> cityToSynopticData = new HashMap<>();
        List<SynopticData> synopticDataList = apiProvider.getAllSynopticStation();
        for (SynopticData synopticData : synopticDataList) {
            String key = synopticData.getCityName();
            if (cityToSynopticData.containsKey(key)) {
                List<SynopticData> value = cityToSynopticData.get(key);
                value.add(synopticData);
                cityToSynopticData.put(key, value);
            } else {
                cityToSynopticData.put(key, Lists.newArrayList(synopticData));
            }
        }
        return cityToSynopticData;
    }

    @Override
    public Map<String, List<AirData>> mapCityToAirData() {
        Map<String, List<AirData>> cityToAirData = new HashMap<>();
        List<AirData> airDataList = mapAirMeasurementLocalizationToAirData();
        for (AirData airData : airDataList) {
            String key = airData.getAirMeasurementLocalization().getAirMeasurementCity().getCity();
            if (cityToAirData.containsKey(key)) {
                List<AirData> value = cityToAirData.get(key);
                value.add(airData);
                cityToAirData.put(key, value);
            } else {
                cityToAirData.put(key, Lists.newArrayList(airData));
            }
        }
        return cityToAirData;
    }

    @Override
    public List<AirData> mapAirMeasurementLocalizationToAirData() {
        Map<Integer, AirMeasurementLocalization> airMeasurementLocalizationMap = apiProvider.getStationLocalization();
        List<AirData> airDataList = apiProvider.getAirData();
        airDataList.forEach(airData -> airData.setAirMeasurementLocalization(airMeasurementLocalizationMap.get(airData.getStationId())));
        return airDataList;
    }
}