package sebaszczen.domain.gios.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LevelDto {
    @JsonProperty("id")
    private int airLevelByNumber;
    @JsonProperty("indexLevelName")
    private String airConditionInWord;

    public void setAirLevelByNumber(int airLevelByNumber) {
        this.airLevelByNumber = airLevelByNumber;
    }

    public void setAirConditionInWord(String airConditionInWord) {
        this.airConditionInWord = airConditionInWord;
    }

    public int getAirLevelByNumber() {
        return airLevelByNumber;
    }

    public String getAirConditionInWord() {
        return airConditionInWord;
    }
}
