package sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import sebaszczen.model.City;

@CrossOrigin(origins = "http://localhost:4200")
@Repository
public interface CityRepository extends JpaRepository<City,Long> {

    public City findCityByName(String name);
    boolean existsAllByName(String name);
}
