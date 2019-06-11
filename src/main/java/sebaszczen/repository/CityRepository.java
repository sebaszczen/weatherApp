package sebaszczen.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sebaszczen.model.cityModel.City;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {

    @Query(value = "select c from City c left join fetch c.airDataList where c.name=:name")
    City findCityWithAirData(@Param("name")String cityName);

    @EntityGraph(value = "cityWithSynopticData", type = EntityGraph.EntityGraphType.LOAD)
    City findCityByName(String name);
    boolean existsAllByName(String name);

    @Query(value = "select c from City c left join fetch c.synopticDataList s")
    List<City> findAllWithSynopticInitialized();

    @Query(value = "select distinct c from City c left join fetch c.airDataList a left join fetch a.stIndexLevel left join fetch a.so2IndexLevel left join fetch a.no2IndexLevel left join fetch a.coIndexLevel left join fetch a.pm10IndexLevel left join fetch a.pm25IndexLevel left join fetch a.c6H6IndexLevel left join fetch a.o3IndexLevel left join fetch a.airMeasurementLocalization ")
    List<City> findAllWithAirInitialized();

//    @EntityGraph(value = "cityWithSynopticDataAndAirData", type = EntityGraph.EntityGraphType.LOAD)
//    List<City> findAll();
}
