package sebaszczen.services;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sebaszczen.model.City;
import sebaszczen.model.SynopticData;
import sebaszczen.model.airModel.AirData;
import sebaszczen.repository.AirDataRepository;
import sebaszczen.repository.SynopticDataRepository;

import java.util.List;

@Service
public class DataService {

    private AirDataRepository airDataRepository;
    private SynopticDataRepository synopticDataRepository;

    @Autowired
    public DataService(AirDataRepository airDataRepository, SynopticDataRepository synopticDataRepository) {
        this.airDataRepository = airDataRepository;
        this.synopticDataRepository = synopticDataRepository;
    }

    public City findLastDataForCityName(String name){
        List<AirData> airDataList = airDataRepository.findLastDataforCity(name);
        List<SynopticData> synopticDataList = synopticDataRepository.findLastDataforCity(name);
        String cityName =  synopticDataList.get(0).getCityName();
        return new City(cityName, synopticDataList, airDataList);
    }
}
