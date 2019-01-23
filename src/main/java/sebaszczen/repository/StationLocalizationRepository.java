package sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sebaszczen.model.StationLocalization;

@Repository
public interface StationLocalizationRepository extends JpaRepository<StationLocalization, Long> {


}
