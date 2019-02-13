package sebaszczen.model;

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
    private List<SynopticData> synopticData;

    public City() {
    }

    public City(String name, List<SynopticData> synopticData) {
        this.name = name;
        this.synopticData = synopticData;
    }
}
