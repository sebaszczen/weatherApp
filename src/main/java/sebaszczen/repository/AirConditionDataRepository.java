package sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sebaszczen.domain.gios.AirConditionData;

@Repository
public interface AirConditionDataRepository extends JpaRepository<AirConditionData,Long>{
}
