package sebaszczen.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sebaszczen.dto.AirDataDto;
import sebaszczen.dto.CityDto;
import sebaszczen.exception.NotFoundException;
import sebaszczen.model.City;
import sebaszczen.model.SynopticData;
import sebaszczen.model.airModel.AirData;
import sebaszczen.repository.AirDataRepository;
import sebaszczen.repository.SynopticDataRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DataService {

    private Logger logger = LoggerFactory.getLogger(DataService.class);
    private AirDataRepository airDataRepository;
    private SynopticDataRepository synopticDataRepository;
    private ConvertBetweenEntityAndResource convertBetweenEntityAndResource;

    @Autowired
    public DataService(AirDataRepository airDataRepository, SynopticDataRepository synopticDataRepository, ConvertBetweenEntityAndResource convertBetweenEntityAndResource) {
        this.airDataRepository = airDataRepository;
        this.synopticDataRepository = synopticDataRepository;
        this.convertBetweenEntityAndResource = convertBetweenEntityAndResource;
    }

//    @Cacheable(value = "findLastDataForCityName", key = "#name")
    public CityDto findLastDataForCityName(String name) {
        Optional<List<AirData>> airDataList = getLastAirDataforCity(name);
        Optional<List<SynopticData>> synopticDataList = synopticDataRepository.findLastDataforCity(name);
            return new CityDto(new City(name, synopticDataList.get(), airDataList.get()));
//        List<AirDataDto> airDataDtoList = new ArrayList<>();
//        List<SynopticData.SynoptiDataDto> synoptiDataDtos = new ArrayList<>();
//        if (airDataList.isPresent()) {
//            airDataList.get().forEach(x->{
//                AirDataDto target = new AirDataDto();
//                BeanUtils.copyProperties(x, target);
//                airDataDtoList.add(target);
//            });
//        }
//        if (synopticDataList.isPresent()) {
//            synopticDataList.get().forEach(x->{
//                SynopticData.SynoptiDataDto target = new SynopticData.SynoptiDataDto();
//                BeanUtils.copyProperties(x, target);
//                synoptiDataDtos.add(target);
//            });
//        }
//        return new CityDto(cityName,synoptiDataDtos,airDataDtoList);
    }

    public Optional<List<AirData>> getLastAirDataforCity(String name) {
        return airDataRepository.findLastDataforCity(name);
    }

}
