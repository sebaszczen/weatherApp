package sebaszczen.model;

import sebaszczen.model.airModel.AirData;

import javax.persistence.*;
import java.util.List;

@Entity
public class City {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "city")
    private List<SynopticData> synopticDataList;
    @OneToMany(mappedBy ="city")
    private List<AirData> airDataList;

    public City() {
    }

    public City(List<AirData> airDataList, String name) {
        this.name = name;
        this.airDataList = airDataList;
    }

    public City(String cityName, List<SynopticData> synopticDataList) {
        this.name = cityName;
        this.synopticDataList = synopticDataList;
    }
}
