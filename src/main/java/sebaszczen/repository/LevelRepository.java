package sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sebaszczen.model.airModel.AirQuality;

public interface LevelRepository extends JpaRepository<AirQuality,Long> {
}
