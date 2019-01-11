package sebaszczen.services.api;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import sebaszczen.domain.Weather;

public interface SynopticDataService {
    void saveSynopticData();

    void saveAirConditions();
}
