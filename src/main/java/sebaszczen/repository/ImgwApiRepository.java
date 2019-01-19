package sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sebaszczen.domain.SynopticStation;

@Repository
public interface ImgwApiRepository extends JpaRepository<SynopticStation,Long> {

    @Query(value = "select count(*) from synoptic_station" +
            " where extract(hour from local_date_time)=:hour and extract(day from local_date_time)=:day",nativeQuery = true)
    public int checkIfContain(@Param("hour")int hour, @Param("day")int day);
}
