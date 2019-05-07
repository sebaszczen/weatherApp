package sebaszczen.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sebaszczen.dto.CityDto;
import sebaszczen.model.cityModel.City;
import sebaszczen.model.synopticModel.SynopticData;
import sebaszczen.model.airModel.AirData;
import sebaszczen.repository.AirDataRepository;
import sebaszczen.repository.SynopticDataRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DataService {

    private Logger logger = LoggerFactory.getLogger(DataService.class);
    private AirDataRepository airDataRepository;
    private SynopticDataRepository synopticDataRepository;

    @Autowired
    public DataService(AirDataRepository airDataRepository, SynopticDataRepository synopticDataRepository) {
        this.airDataRepository = airDataRepository;
        this.synopticDataRepository = synopticDataRepository;
    }

    //    @Cacheable(value = "findLastDataForCityName", key = "#name")
    public CityDto findLastDataForCityName(String name) {
        Optional<List<AirData>> airDataList = getLastAirDataforCity(name);
        Optional<List<SynopticData>> synopticDataList = synopticDataRepository.findLastDataforCity(name);
        return new CityDto(new City(name, synopticDataList.get(), airDataList.get()));
    }

    public Optional<List<AirData>> getLastAirDataforCity(String name) {
        return airDataRepository.findLastDataforCity(name);
    }

}
