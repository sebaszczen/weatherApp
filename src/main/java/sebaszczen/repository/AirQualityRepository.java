package sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sebaszczen.model.airModel.AirQuality;

public interface AirQualityRepository extends JpaRepository<AirQuality,Long> {
}
