package sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sebaszczen.domain.SynopticStation;

@Repository
public interface SynopticConditionsRepository extends JpaRepository<SynopticStation,Long> {

}
