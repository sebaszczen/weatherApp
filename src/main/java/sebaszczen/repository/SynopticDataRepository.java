package sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sebaszczen.model.SynopticData;
import sebaszczen.model.airModel.AirData;

import java.util.List;

@Repository
public interface SynopticDataRepository extends JpaRepository<SynopticData,Long> {

    @Query(value = "select count(*) from synoptic_data" +
            " where extract(hour from local_date_time)=:hour and extract(day from local_date_time)=:day",
            nativeQuery = true)
    int contain(@Param("hour")int hour, @Param("day")int day);

    @Query(value = "select s from SynopticData s where s.localDateTime=(select max(s.localDateTime)" +
            " from SynopticData s where s.city.name=:name ) and s.city.name=:name")
    public List<SynopticData> findLastDataforCity(@Param("name") String name);
}
