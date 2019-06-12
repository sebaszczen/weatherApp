package sebaszczen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sebaszczen.model.cityModel.City;
import sebaszczen.repository.CityRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    @Transactional
    public List<City> findAll() {
//        List<City> all = cityRepository.findAll();
//        cityRepository.findAll().stream().forEach(y->{
//            InitializeModelService.initializedCollection(y.getSynopticDataList());
//                });
//        return cityRepository.findAll().stream().filter(Objects::nonNull).map(x->{
//            List<AirData> airDataList = x.getAirDataList();
//            airDataList.stream().filter(Objects::nonNull).forEach(airData -> {
//                InitializeModelService.initializedCollection(airData.getAirMeasurementLocalization());
//                InitializeModelService.initializedCollection(airData.getStIndexLevel());
//                InitializeModelService.initializedCollection(airData.getSo2IndexLevel());
//                InitializeModelService.initializedCollection(airData.getPm25IndexLevel());
//                InitializeModelService.initializedCollection(airData.getPm10IndexLevel());
//                InitializeModelService.initializedCollection(airData.getO3IndexLevel());
//                InitializeModelService.initializedCollection(airData.getNo2IndexLevel());
//                InitializeModelService.initializedCollection(airData.getC6H6IndexLevel());
//            });
//            return x;
//                }).collect(Collectors.toList());
//        return all;
        List<City> allWithAirInitialized = cityRepository.findAllWithAirInitialized();
        List<City> allWithSynopticInitialized = cityRepository.findAllWithSynopticInitialized();
        allWithAirInitialized.stream().forEach(a->
                a.addSynopticData(allWithSynopticInitialized.stream().filter(x -> x.getName() == "warszawa").
                map(c -> c.getSynopticDataList()).flatMap(q->q.stream()).collect(Collectors.toList())));

        return allWithAirInitialized;
    }
}
