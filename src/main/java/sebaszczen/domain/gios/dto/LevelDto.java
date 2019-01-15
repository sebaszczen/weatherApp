package sebaszczen.domain.gios.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LevelDto {
    @JsonProperty("id")
    private Long airLevelByNumber;
    @JsonProperty("indexLevelName")
    private String airConditionInWord;

    public void setAirLevelByNumber(Long airLevelByNumber) {
        this.airLevelByNumber = airLevelByNumber;
    }

    public void setAirConditionInWord(String airConditionInWord) {
        this.airConditionInWord = airConditionInWord;
    }

    public Long getAirLevelByNumber() {
        return airLevelByNumber;
    }

    public String getAirConditionInWord() {
        return airConditionInWord;
    }
}
