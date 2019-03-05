package sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sebaszczen.model.airModel.AirData;

import java.util.List;

@Repository
public interface AirDataRepository extends JpaRepository<AirData,Long>{

    @Query(value = "select count(*) from air_data " +
            "where extract(hour from st_calc_date)=:hour and extract(day from st_calc_date)=:day and " +
            "extract(month from st_calc_date)=:month and extract(year from st_calc_date)=:year",nativeQuery = true)
    public int contain(@Param("hour")int hour, @Param("day")int day, @Param("month")int month, @Param("year") int year);

    @Query(value = "select a from AirData a where a.stCalcDate=(select max(a.stCalcDate)" +
            " from AirData a where a.city.name=:name ) and a.city.name=:name")
    public List<AirData> findLastDataforCity(@Param("name") String name);
}
