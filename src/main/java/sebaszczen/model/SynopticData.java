package sebaszczen.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class SynopticData {
    @Id
    @GeneratedValue
    private Long id;
    private String city;
    private LocalDateTime localDateTime;
    private float temperatura;
    private float predkosc_wiatru;
    private int kierunek_wiatru;
    private float wilgotnosc_wzgledna;
    private float suma_opadu;
    private float cisnienie;

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public SynopticData() {
    }

    public SynopticData(SynopticStationDto synopticStationDto) {
        this.city = synopticStationDto.stacja;
//        this.localDateTime = LocalDateTime.of(Optional.ofNullable(synopticStationDto.data_pomiaru).orElse(LocalDate.of(0,1,1)), Optional.ofNullable(synopticStationDto.godzina_pomiaru).orElse(LocalTime.of(0,0,0)));
        this.localDateTime = LocalDateTime.of(synopticStationDto.getData_pomiaru(), synopticStationDto.getGodzina_pomiaru());
        this.temperatura = synopticStationDto.temperatura;
        this.predkosc_wiatru = synopticStationDto.predkosc_wiatru;
        this.kierunek_wiatru = synopticStationDto.kierunek_wiatru;
        this.wilgotnosc_wzgledna = synopticStationDto.wilgotnosc_wzgledna;
        this.suma_opadu = synopticStationDto.suma_opadu;
        this.cisnienie = synopticStationDto.cisnienie;
    }

    public static class SynopticStationDto {
        private Long id_stacji;
        private String stacja;
        private LocalDate data_pomiaru;
        private LocalTime godzina_pomiaru;
        private float temperatura;
        private float predkosc_wiatru;
        private int kierunek_wiatru;
        private float wilgotnosc_wzgledna;
        private float suma_opadu;
        private float cisnienie;

        public SynopticStationDto setId_stacji(Long id_stacji) {
            this.id_stacji = id_stacji;
            return this;
        }

        public SynopticStationDto setStacja(String stacja) {
            this.stacja = stacja;
            return this;
        }

        public SynopticStationDto setData_pomiaru(LocalDate data_pomiaru) {
            this.data_pomiaru = data_pomiaru;
            return this;
        }

        public SynopticStationDto setGodzina_pomiaru(int godzina_pomiaru) {
            this.godzina_pomiaru = LocalTime.of(godzina_pomiaru, 0, 0, 0);
            return this;
        }

        public SynopticStationDto setTemperatura(float temperatura) {
            this.temperatura = temperatura;
            return this;
        }

        public SynopticStationDto setPredkosc_wiatru(float predkosc_wiatru) {
            this.predkosc_wiatru = predkosc_wiatru;
            return this;
        }

        public SynopticStationDto setKierunek_wiatru(int kierunek_wiatru) {
            this.kierunek_wiatru = kierunek_wiatru;
            return this;
        }

        public SynopticStationDto setWilgotnosc_wzgledna(float wilgotnosc_wzgledna) {
            this.wilgotnosc_wzgledna = wilgotnosc_wzgledna;
            return this;
        }

        public SynopticStationDto setSuma_opadu(float suma_opadu) {
            this.suma_opadu = suma_opadu;
            return this;
        }

        public SynopticStationDto setCisnienie(float cisnienie) {
            this.cisnienie = cisnienie;
            return this;
        }

        public SynopticData convertToEntity() {
            return new SynopticData(this);
        }

        public Long getId_stacji() {
            return id_stacji;
        }

        public String getStacja() {
            return stacja;
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
                Objects.equals(id, that.id) &&
                Objects.equals(city, that.city) &&
                Objects.equals(localDateTime, that.localDateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, city, localDateTime, temperatura, predkosc_wiatru, kierunek_wiatru, wilgotnosc_wzgledna, suma_opadu, cisnienie);
    }
}
