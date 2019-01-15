package sebaszczen.domain.gios;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Level {
    private Long airLevelByNumber;
    private String airConditionInWord;

    public void setAirLevelByNumber(Long airLevelByNumber) {
        this.airLevelByNumber = airLevelByNumber;
    }

    public void setAirConditionInWord(String airConditionInWord) {
        this.airConditionInWord = airConditionInWord;
    }
}
