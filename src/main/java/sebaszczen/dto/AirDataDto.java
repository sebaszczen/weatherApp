package sebaszczen.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.transaction.annotation.Transactional;
import sebaszczen.controller.DataController;
import sebaszczen.model.airModel.AirData;
import sebaszczen.model.airModel.AirQuality;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class AirDataDto extends ResourceSupport {
    @JsonProperty("id")
    private int stationId;
    private LocalDateTime stCalcDate;
    private AirQualityDto stIndexLevel; //powietrze ogólnie
    private AirQualityDto so2IndexLevel; //dwutlenek siarki
    private String so2SourceDataDate;
    private AirQualityDto no2IndexLevel; //dwutlenek azotu
    private AirQualityDto coIndexLevel;//tlenek wegla
    private String coSourceDataDate;
    private AirQualityDto pm10IndexLevel; //pył zawieszony PM10
    private AirQualityDto pm25IndexLevel;
    private AirQualityDto o3IndexLevel;
    private AirQualityDto c6h6IndexLevel;

    public AirDataDto(AirData airData) {
        this.stationId=airData.getStationId();
        this.stCalcDate = airData.getStCalcDate();
        BeanUtils.copyProperties(initializeAndUnproxy(airData.getStIndexLevel()),stIndexLevel=new AirQualityDto());
        BeanUtils.copyProperties(initializeAndUnproxy(airData.getSo2IndexLevel()),so2IndexLevel=new AirQualityDto());
        BeanUtils.copyProperties(initializeAndUnproxy(airData.getNo2IndexLevel()),no2IndexLevel=new AirQualityDto());
        BeanUtils.copyProperties(initializeAndUnproxy(airData.getCoIndexLevel()),coIndexLevel=new AirQualityDto());
        BeanUtils.copyProperties(initializeAndUnproxy(airData.getPm10IndexLevel()),pm10IndexLevel=new AirQualityDto());
        BeanUtils.copyProperties(initializeAndUnproxy(airData.getPm25IndexLevel()),pm25IndexLevel=new AirQualityDto());
        BeanUtils.copyProperties(initializeAndUnproxy(airData.getO3IndexLevel()),o3IndexLevel=new AirQualityDto());
        BeanUtils.copyProperties(initializeAndUnproxy(airData.getC6H6IndexLevel()),c6h6IndexLevel=new AirQualityDto());
    }

    public static <T> T initializeAndUnproxy(T entity) {
        if (entity == null) {
            throw new
                    NullPointerException("Entity passed for initialization is null");
        }

        Hibernate.initialize(entity);
        if (entity instanceof HibernateProxy) {
            entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
                    .getImplementation();
        }
        return entity;
    }

    public AirDataDto() {
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public void setStCalcDate(String stCalcDate) {
        String[] split = Optional.ofNullable(stCalcDate).isPresent()?stCalcDate.split(" "):null;
        this.stCalcDate = split==null?null: LocalDateTime.of(LocalDate.parse(split[0]), LocalTime.parse(split[1]));
    }


    public void setStCalcDate(LocalDateTime stCalcDate) {
        this.stCalcDate = stCalcDate;
    }

    public AirData convertToEntity() {
        return new AirData(this);
    }

    public void setStIndexLevel(AirQualityDto stIndexLevel) {
        this.stIndexLevel = stIndexLevel;
    }

    public void setSo2IndexLevel(AirQualityDto so2IndexLevel) {
        this.so2IndexLevel = so2IndexLevel;
    }

    public void setSo2SourceDataDate(String so2SourceDataDate) {
        this.so2SourceDataDate = so2SourceDataDate;
    }

    public void setNo2IndexLevel(AirQualityDto no2IndexLevel) {
        this.no2IndexLevel = no2IndexLevel;
    }

    public void setCoIndexLevel(AirQualityDto coIndexLevel) {
        this.coIndexLevel = coIndexLevel;
    }

    public void setCoSourceDataDate(String coSourceDataDate) {
        this.coSourceDataDate = coSourceDataDate;
    }

    public void setPm10IndexLevel(AirQualityDto pm10IndexLevel) {
        this.pm10IndexLevel = pm10IndexLevel;
    }

    public void setPm25IndexLevel(AirQualityDto pm25IndexLevel) {
        this.pm25IndexLevel = pm25IndexLevel;
    }

    public void setO3IndexLevel(AirQualityDto o3IndexLevel) {
        this.o3IndexLevel = o3IndexLevel;
    }

    public void setC6h6IndexLevel(AirQualityDto c6h6IndexLevel) {
        this.c6h6IndexLevel = c6h6IndexLevel;
    }

    public int getStationId() {
        return stationId;
    }

    public LocalDateTime getStCalcDate() {
        return stCalcDate;
    }

    public AirQualityDto getStIndexLevel() {
        return stIndexLevel;
    }

    public AirQualityDto getSo2IndexLevel() {
        return so2IndexLevel;
    }

    public String getSo2SourceDataDate() {
        return so2SourceDataDate;
    }

    public AirQualityDto getNo2IndexLevel() {
        return no2IndexLevel;
    }

    public AirQualityDto getCoIndexLevel() {
        return coIndexLevel;
    }

    public String getCoSourceDataDate() {
        return coSourceDataDate;
    }

    public AirQualityDto getPm10IndexLevel() {
        return pm10IndexLevel;
    }

    public AirQualityDto getPm25IndexLevel() {
        return pm25IndexLevel;
    }

    public AirQualityDto getO3IndexLevel() {
        return o3IndexLevel;
    }

    public AirQualityDto getC6h6IndexLevel() {
        return c6h6IndexLevel;
    }

}
