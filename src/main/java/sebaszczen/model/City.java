package sebaszczen.model;

import org.hibernate.annotations.IndexColumn;
import sebaszczen.model.airModel.AirData;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
//@Table(indexes = {@Index(columnList ="name, id" ,name="indeks")})
public class City {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private List<SynopticData> synopticDataList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private List<AirData> airDataList;

    public City() {
    }

    public City(String name, List<SynopticData> synopticDataList, List<AirData> airDataList) {
        this.name = name;
        this.synopticDataList = synopticDataList;
        this.airDataList = airDataList;
    }

    public String getName() {
        return name;
    }

    public List<SynopticData> getSynopticDataList() {
        return synopticDataList;
    }

    public void setSynopticDataList(List<SynopticData> synopticDataList) {
        this.synopticDataList = synopticDataList;
    }

    public List<AirData> getAirDataList() {
        return airDataList;
    }

    public void setAirDataList(List<AirData> airDataList) {
        this.airDataList = airDataList;
    }
}
