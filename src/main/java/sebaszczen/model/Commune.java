package sebaszczen.model;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Commune {
    private String communeName;
    private String districtName;
    private String provinceName;

    public Commune(String communeName, String districtName, String provinceName) {
        this.communeName = communeName;
        this.districtName = districtName;
        this.provinceName = provinceName;
    }

    public Commune() {
    }

    public void setCommuneName(String communeName) {
        this.communeName = communeName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commune commune = (Commune) o;
        return Objects.equals(communeName, commune.communeName) &&
                Objects.equals(districtName, commune.districtName) &&
                Objects.equals(provinceName, commune.provinceName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(communeName, districtName, provinceName);
    }
}
