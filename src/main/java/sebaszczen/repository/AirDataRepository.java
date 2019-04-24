package sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sebaszczen.model.airModel.AirData;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirDataRepository extends JpaRepository<AirData,Long>{

    @Query(value = "select count(*) from air_data " +
            "where extract(hour from st_calc_date)=:hour and extract(day from st_calc_date)=:day and " +
            "extract(month from st_calc_date)=:month and extract(year from st_calc_date)=:year",nativeQuery = true)
    public int contain(@Param("hour")int hour, @Param("day")int day, @Param("month")int month, @Param("year") int year);

    @Query(value = "select distinct a from AirData a left join fetch a.stIndexLevel left join fetch a.so2IndexLevel left join fetch a.no2IndexLevel left join fetch a.coIndexLevel left join fetch a.pm10IndexLevel left join fetch a.pm25IndexLevel left join fetch a.c6H6IndexLevel left join fetch a.o3IndexLevel left join fetch a.airMeasurementLocalization where a.stCalcDate=(select max(a.stCalcDate)" +
            " from AirData a where a.city.name=:name ) and a.city.name=:name")
    public Optional<List<AirData>> findLastDataforCity(@Param("name") String name);
}
