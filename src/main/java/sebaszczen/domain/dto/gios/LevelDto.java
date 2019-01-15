package sebaszczen.domain.dto.gios;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LevelDto {
    @JsonProperty("airLevelByNumber")
    private Long airLevelByNumber;
    private String airConditionInWord;

    public void setAirLevelByNumber(Long airLevelByNumber) {
        this.airLevelByNumber = airLevelByNumber;
    }

    public void setAirConditionInWord(String airConditionInWord) {
        this.airConditionInWord = airConditionInWord;
    }
}
