package sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sebaszczen.model.airModel.AirMeasurementLocalization;

@Repository
public interface StationLocalizationRepository extends JpaRepository<AirMeasurementLocalization, Long> {


}
