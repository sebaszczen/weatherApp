package sebaszczen.model;

import org.hibernate.Hibernate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.transaction.annotation.Transactional;
import sebaszczen.model.airModel.AirData;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(indexes = {@Index(columnList ="name, id" ,name="indeks")})
@NamedEntityGraph(name = "cityWithSynopticData", attributeNodes = { @NamedAttributeNode("synopticDataList") })
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(fetch =FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "city", orphanRemoval = true)
    private List<SynopticData> synopticDataList= new ArrayList<>();
    @OneToMany(fetch =FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "city", orphanRemoval = true)
    private List<AirData> airDataList= new ArrayList<>();

    public City() {
    }

    public City(String name, List<SynopticData> synopticDataList, List<AirData> airDataList) {
        this.name = name;
        if (synopticDataList!=null) {
            addSynopticData(synopticDataList);
        }
        if (airDataList!=null) {
            addAirData(airDataList);
        }
    }

    public void addSynopticData(List<SynopticData> synopticData) {
        synopticDataList.addAll(synopticData);
        synopticData.forEach(synopticData1 -> synopticData1.setCity(this));
    }

    public void addAirData(List<AirData> airData) {
        getAirDataList().addAll(airData);
        airData.forEach(airData1 -> airData1.setCity(this));
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
