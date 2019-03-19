package sebaszczen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sebaszczen.exception.NotFoundException;
import sebaszczen.model.City;
import sebaszczen.model.SynopticData;
import sebaszczen.model.airModel.AirData;
import sebaszczen.repository.AirDataRepository;
import sebaszczen.repository.SynopticDataRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DataService {

    private AirDataRepository airDataRepository;
    private SynopticDataRepository synopticDataRepository;

    @Autowired
    public DataService(AirDataRepository airDataRepository, SynopticDataRepository synopticDataRepository) {
        this.airDataRepository = airDataRepository;
        this.synopticDataRepository = synopticDataRepository;
    }

//    @Cacheable(value = "findLastDataForCityName", key = "#name")
    public City findLastDataForCityName(String name) throws NotFoundException {
        Optional<List<AirData>> airDataList = airDataRepository.findLastDataforCity(name);
        List<AirData> airDataList1 = airDataList.get();
        Optional<List<SynopticData>> synopticDataList = synopticDataRepository.findLastDataforCity(name);
        return synopticDataList.isPresent() ? new City(synopticDataList.get().get(0).getCityName(), synopticDataList.get(), airDataList1) : null;
    }

}
