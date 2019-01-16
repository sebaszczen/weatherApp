package sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sebaszczen.domain.gios.AirConditionData;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public interface AirConditionDataRepository extends JpaRepository<AirConditionData,Long>{



}
