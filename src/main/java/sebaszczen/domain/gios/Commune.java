package sebaszczen.domain.gios;

import javax.persistence.Embeddable;

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
}
