package sebaszczen.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class SynopticData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_stacji;
    private String cityName;
    //    @JsonDeserialize(using = LocalDateDeserializer.class)
    private float temperatura;
    private float predkosc_wiatru;
    private int kierunek_wiatru;
    private float wilgotnosc_wzgledna;
    private float suma_opadu;
    private float cisnienie;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private LocalDateTime localDateTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    @JsonIgnore
    private City city;

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public SynopticData() {
    }

    public SynopticData(SynoptiDataDto synoptiDataDto) {
        this.cityName = synoptiDataDto.cityName;
//        this.localDateTime = LocalDateTime.of(Optional.ofNullable(synoptiDataDto.data_pomiaru).orElse(LocalDate.of(0,1,1)), Optional.ofNullable(synoptiDataDto.godzina_pomiaru).orElse(LocalTime.of(0,0,0)));
        this.localDateTime = LocalDateTime.of(synoptiDataDto.getData_pomiaru(), synoptiDataDto.getGodzina_pomiaru());
        this.temperatura = synoptiDataDto.temperatura;
        this.predkosc_wiatru = synoptiDataDto.predkosc_wiatru;
        this.kierunek_wiatru = synoptiDataDto.kierunek_wiatru;
        this.wilgotnosc_wzgledna = synoptiDataDto.wilgotnosc_wzgledna;
        this.suma_opadu = synoptiDataDto.suma_opadu;
        this.cisnienie = synoptiDataDto.cisnienie;
    }

    @JsonIgnore
    public City getCity() {
        return city;
    }

    public Long getId_stacji() {
        return id_stacji;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float getPredkosc_wiatru() {
        return predkosc_wiatru;
    }

    public void setPredkosc_wiatru(float predkosc_wiatru) {
        this.predkosc_wiatru = predkosc_wiatru;
    }

    public int getKierunek_wiatru() {
        return kierunek_wiatru;
    }

    public void setKierunek_wiatru(int kierunek_wiatru) {
        this.kierunek_wiatru = kierunek_wiatru;
    }

    public float getWilgotnosc_wzgledna() {
        return wilgotnosc_wzgledna;
    }

    public void setWilgotnosc_wzgledna(float wilgotnosc_wzgledna) {
        this.wilgotnosc_wzgledna = wilgotnosc_wzgledna;
    }

    public float getSuma_opadu() {
        return suma_opadu;
    }

    public void setSuma_opadu(float suma_opadu) {
        this.suma_opadu = suma_opadu;
    }

    public float getCisnienie() {
        return cisnienie;
    }

    public void setCisnienie(float cisnienie) {
        this.cisnienie = cisnienie;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public static class SynoptiDataDto extends ResourceSupport {
        private Long id_stacji;
        @JsonProperty("stacja")
        private String cityName;
        private LocalDate data_pomiaru;
        private LocalTime godzina_pomiaru;
        private float temperatura;
        private float predkosc_wiatru;
        private int kierunek_wiatru;
        private float wilgotnosc_wzgledna;
        private float suma_opadu;
        private float cisnienie;

        public void setGodzina_pomiaru(LocalTime godzina_pomiaru) {
            this.godzina_pomiaru = godzina_pomiaru;
        }

        public SynoptiDataDto setId_stacji(Long id_stacji) {
            this.id_stacji = id_stacji;
            return this;
        }

        public SynoptiDataDto setCityName(String cityName) {
            this.cityName = cityName;
            return this;
        }

        public SynoptiDataDto setData_pomiaru(LocalDate data_pomiaru) {
            this.data_pomiaru = data_pomiaru;
            return this;
        }

        public SynoptiDataDto setGodzina_pomiaru(int godzina_pomiaru) {
            this.godzina_pomiaru = LocalTime.of(godzina_pomiaru, 0, 0, 0);
            return this;
        }

        public SynoptiDataDto setTemperatura(float temperatura) {
            this.temperatura = temperatura;
            return this;
        }

        public SynoptiDataDto setPredkosc_wiatru(float predkosc_wiatru) {
            this.predkosc_wiatru = predkosc_wiatru;
            return this;
        }

        public SynoptiDataDto setKierunek_wiatru(int kierunek_wiatru) {
            this.kierunek_wiatru = kierunek_wiatru;
            return this;
        }

        public SynoptiDataDto setWilgotnosc_wzgledna(float wilgotnosc_wzgledna) {
            this.wilgotnosc_wzgledna = wilgotnosc_wzgledna;
            return this;
        }

        public SynoptiDataDto setSuma_opadu(float suma_opadu) {
            this.suma_opadu = suma_opadu;
            return this;
        }

        public SynoptiDataDto setCisnienie(float cisnienie) {
            this.cisnienie = cisnienie;
            return this;
        }

        public SynopticData convertToEntity() {
            return new SynopticData(this);
        }

        public Long getId_stacji() {
            return id_stacji;
        }

        public String getCityName() {
            return cityName;
        }

        public LocalDate getData_pomiaru() {
            return data_pomiaru;
        }

        public LocalTime getGodzina_pomiaru() {
            return godzina_pomiaru;
        }

        public float getTemperatura() {
            return temperatura;
        }

        public float getPredkosc_wiatru() {
            return predkosc_wiatru;
        }

        public int getKierunek_wiatru() {
            return kierunek_wiatru;
        }

        public float getWilgotnosc_wzgledna() {
            return wilgotnosc_wzgledna;
        }

        public float getSuma_opadu() {
            return suma_opadu;
        }

        public float getCisnienie() {
            return cisnienie;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SynopticData that = (SynopticData) o;
        return Float.compare(that.temperatura, temperatura) == 0 &&
                Float.compare(that.predkosc_wiatru, predkosc_wiatru) == 0 &&
                kierunek_wiatru == that.kierunek_wiatru &&
                Float.compare(that.wilgotnosc_wzgledna, wilgotnosc_wzgledna) == 0 &&
                Float.compare(that.suma_opadu, suma_opadu) == 0 &&
                Float.compare(that.cisnienie, cisnienie) == 0 &&
                Objects.equals(id_stacji, that.id_stacji) &&
                Objects.equals(cityName, that.cityName) &&
                Objects.equals(localDateTime, that.localDateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id_stacji, cityName, localDateTime, temperatura, predkosc_wiatru, kierunek_wiatru, wilgotnosc_wzgledna, suma_opadu, cisnienie);
    }
}
