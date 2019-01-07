package sebaszczen.services.api;

import sebaszczen.domain.Weather;

public interface ApiSupplier {
    Weather getWeatherMeasurements();
}
