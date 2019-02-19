package sebaszczen.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class AirQualityDto {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AirQualityDto)) return false;
        AirQualityDto airQualityDto = (AirQualityDto) o;
        return airLevelByNumber == airQualityDto.airLevelByNumber &&
                Objects.equals(airConditionInWord, airQualityDto.airConditionInWord);
    }

    @Override
    public int hashCode() {

        return Objects.hash(airLevelByNumber, airConditionInWord);
    }
}
