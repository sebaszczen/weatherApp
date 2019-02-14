package sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sebaszczen.model.airModel.AirData;

@Repository
public interface AirDataRepository extends JpaRepository<AirData,Long>{

    @Query(value = "select count(*) from air_data " +
            "where extract(hour from st_calc_date)=:hour and extract(day from st_calc_date)=:day",nativeQuery = true)
    public int contain(@Param("hour")int hour, @Param("day")int day);

}