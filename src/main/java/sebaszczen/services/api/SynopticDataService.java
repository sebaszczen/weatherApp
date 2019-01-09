package sebaszczen.services.api;

import sebaszczen.domain.Weather;

public interface SynopticDataService {
    void saveWeatherMeasurements();

    void saveAirConditions();
}
