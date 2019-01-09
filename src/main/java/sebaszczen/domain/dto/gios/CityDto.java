package sebaszczen.domain.dto.gios;

public class CityDto {
    private float id;
    private String name;
    private CommuneDto communeDto;

    public void setId(float id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCommuneDto(CommuneDto communeDto) {
        this.communeDto = communeDto;
    }
}
