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

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "city_id")
    private List<SynopticData> synopticDataList;
    @OneToMany
    private List<AirData> airDataList;

    public City() {
    }

    public City(String name, List<SynopticData> synopticDataList) {
        this.name = name;
        this.synopticDataList = synopticDataList;
    }
}
