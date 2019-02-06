package sebaszczen.model;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Commune {
    private String provinceName;

    public Commune(String communeName, String districtName, String provinceName) {
        this.provinceName = provinceName;
    }

    public Commune() {
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commune commune = (Commune) o;
        return Objects.equals(provinceName, commune.provinceName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(provinceName);
    }
}
