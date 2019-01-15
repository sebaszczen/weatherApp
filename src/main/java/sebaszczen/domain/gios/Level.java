package sebaszczen.domain.gios;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Level {
    @Id
    @GeneratedValue
    private Long id;
    private String airConditionInWord;
    @OneToOne
    private AirConditionData airConditionData;

    public Level(String airConditionInWord) {
        this.airConditionInWord = airConditionInWord;
    }

    public void setAirConditionInWord(String airConditionInWord) {
        this.airConditionInWord = airConditionInWord;
    }
}
