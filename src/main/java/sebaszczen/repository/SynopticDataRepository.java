package sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sebaszczen.model.SynopticData;

@Repository
public interface SynopticDataRepository extends JpaRepository<SynopticData,Long> {

    @Query(value = "select count(*) from synoptic_data" +
            " where extract(hour from local_date_time)=:hour and extract(day from local_date_time)=:day",nativeQuery = true)
    int contain(@Param("hour")int hour, @Param("day")int day);
}
