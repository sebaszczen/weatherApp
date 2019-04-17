package sebaszczen.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import sebaszczen.model.City;

import javax.persistence.JoinColumn;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Repository
public interface CityRepository extends JpaRepository<City,Long> {

    @Query(value = "select c from City c left join fetch c.airDataList where c.name=:name")
    City findCityWithAirData(@Param("name")String cityName);

    @EntityGraph(value = "cityWithSynopticData", type = EntityGraph.EntityGraphType.LOAD)
    City findCityByName(String name);
    boolean existsAllByName(String name);
}
