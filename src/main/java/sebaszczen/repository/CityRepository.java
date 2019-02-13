package sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sebaszczen.model.City;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {
}
