package sebaszczen.model.airModel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AirQuality {
    @Id
    private Long id;
    private int airConditionLevel;
    private String airConditionInWord;
    private boolean saved=false;

    public AirQuality() {
    }

    public AirQuality(Long id, String airConditionInWord, int airLevelByNumber) {
        this.id=id;
        this.airConditionInWord = airConditionInWord;
        this.airConditionLevel = airLevelByNumber;
    }

    public void setAirConditionInWord(String airConditionInWord) {
        this.airConditionInWord = airConditionInWord;
    }

    public void setAirConditionLevel(int airConditionLevel) {
        this.airConditionLevel = airConditionLevel;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AirQuality)) return false;
        AirQuality airQuality = (AirQuality) o;
        return airConditionLevel == airQuality.airConditionLevel &&
                Objects.equals(airConditionInWord, airQuality.airConditionInWord);
    }

    @Override
    public int hashCode() {

        return Objects.hash(airConditionLevel, airConditionInWord);
    }

    public int getAirConditionLevel() {
        return airConditionLevel;
    }

    public String getAirConditionInWord() {
        return airConditionInWord;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public boolean isSaved() {
        return saved;
    }

    @Override
    public String toString() {
        return "AirQuality{" +
                "id=" + id +
                ", airConditionLevel=" + airConditionLevel +
                ", airConditionInWord='" + airConditionInWord + '\'' +
                ", saved=" + saved +
                '}';
    }
}
