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
    private int airConditionLevel;
    private String airConditionInWord;

    public Level() {
    }

    public Level(String airConditionInWord, int airLevelByNumber) {
        this.airConditionInWord = airConditionInWord;
        this.airConditionLevel = airLevelByNumber;
    }

    public void setAirConditionInWord(String airConditionInWord) {
        this.airConditionInWord = airConditionInWord;
    }

    public void setAirConditionLevel(int airConditionLevel) {
        this.airConditionLevel = airConditionLevel;
    }
}
