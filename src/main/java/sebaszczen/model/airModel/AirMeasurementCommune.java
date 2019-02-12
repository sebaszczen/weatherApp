package sebaszczen.model.airModel;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class AirMeasurementCommune {
    private String provinceName;

    public AirMeasurementCommune(String provinceName) {
        this.provinceName = provinceName;
    }

    public AirMeasurementCommune() {
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirMeasurementCommune airMeasurementCommune = (AirMeasurementCommune) o;
        return Objects.equals(provinceName, airMeasurementCommune.provinceName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(provinceName);
    }
}
