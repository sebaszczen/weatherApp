package sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sebaszczen.domain.gios.AirConditionData;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public interface AirConditionDataRepository extends JpaRepository<AirConditionData,Long>{

    @Query(value = "select count(*) from air_condition_data " +
            "where extract(hour from st_calc_date)=:hour and extract(day from st_calc_date)=:day",nativeQuery = true)
    public int checkIfContain(@Param("hour")int hour, @Param("day")int day);

}
