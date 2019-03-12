package sebaszczen.services;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import sebaszczen.exception.NotFoundException;
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

    @Cacheable(value ="findLastDataForCityName", key = "#name")
    public City findLastDataForCityName(String name){
        List<AirData> airDataList = airDataRepository.findLastDataforCity(name).orElseThrow(()->new NotFoundException());
        List<SynopticData> synopticDataList = synopticDataRepository.findLastDataforCity(name).orElseThrow(()->new NotFoundException());
        String cityName =  synopticDataList.get(0).getCityName();
        return new City(cityName, synopticDataList, airDataList);
    }
}
