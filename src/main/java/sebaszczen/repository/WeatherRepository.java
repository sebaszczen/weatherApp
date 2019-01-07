package sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sebaszczen.domain.Weather;

@Repository
public interface WeatherRepository extends JpaRepository<Weather,Long> {

}
