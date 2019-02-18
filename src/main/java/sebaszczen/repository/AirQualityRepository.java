package sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sebaszczen.model.airModel.AirQuality;

@Repository
public interface AirQualityRepository extends JpaRepository<AirQuality,Long> {
}
