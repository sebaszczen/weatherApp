package sebaszczen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sebaszczen.model.City;
import sebaszczen.repository.AirMeasurementLocalizationRepository;
import sebaszczen.repository.CityRepository;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;
    private AirMeasurementLocalizationRepository airMeasurementLocalizationRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, AirMeasurementLocalizationRepository airMeasurementLocalizationRepository) {
        this.cityRepository = cityRepository;
        this.airMeasurementLocalizationRepository = airMeasurementLocalizationRepository;
    }

    @Override
    public List<City> findAll() {
        airMeasurementLocalizationRepository.findById(1L);
        List<City> all = cityRepository.findAll();
        for (City city : all) {
            System.out.println(city.getAirDataList());
        }
        return all;
    }
}
