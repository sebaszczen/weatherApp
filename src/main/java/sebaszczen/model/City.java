package sebaszczen.model;

import sebaszczen.model.airModel.AirData;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class City {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany( cascade = CascadeType.PERSIST)
    @JoinColumn(name = "city_id")
    private List<SynopticData> synopticDataList;
    @NotNull
    @OneToMany( cascade = CascadeType.PERSIST)
    @JoinColumn
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
