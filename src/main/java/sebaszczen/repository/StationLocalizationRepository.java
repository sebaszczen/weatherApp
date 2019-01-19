package sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sebaszczen.domain.gios.StationLocalization;

@Repository
public interface StationLocalizationRepository extends JpaRepository<StationLocalization, Long> {


}
