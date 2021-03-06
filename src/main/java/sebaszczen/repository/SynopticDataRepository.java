package sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sebaszczen.model.synopticModel.SynopticData;

import java.util.List;
import java.util.Optional;

@Repository
public interface SynopticDataRepository extends JpaRepository<SynopticData,Long> {

    @Query(value = "select count(*) from synoptic_data" +
            " where extract(hour from local_date_time)=:hour and extract(day from local_date_time)=:day and extract(month from local_date_time)=:month and extract(year from local_date_time)=:year",
            nativeQuery = true)
    int contain(@Param("hour")int hour, @Param("day")int day, @Param("month")int month, @Param("year")int year);

    @Query(value = "select s from SynopticData s where s.localDateTime=(select max(s.localDateTime)" +
            " from SynopticData s where s.city.name=:name ) and s.city.name=:name")
    public Optional<List<SynopticData>> findLastDataforCity(@Param("name") String name);
}
