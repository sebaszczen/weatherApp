package sebaszczen.domain.gios.dto;

public class CommuneDto {
    private String communeName;
    private String districtName;
    private String provinceName;

    public void setCommuneName(String communeName) {
        this.communeName = communeName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCommuneName() {
        return communeName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public String getProvinceName() {
        return provinceName;
    }
}
