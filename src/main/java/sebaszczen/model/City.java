package sebaszczen.model;

import sebaszczen.model.airModel.AirData;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(indexes = {@Index(columnList ="name, id" ,name="indeks")})
public class City {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city", orphanRemoval = true)
    private List<SynopticData> synopticDataList= new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city", orphanRemoval = true)
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
        airDataList.addAll(airData);
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
