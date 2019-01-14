package sebaszczen.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

@Entity
public class SynopticStation {
    @Id
    @GeneratedValue
    private Long id;
    private Long id_stacji;
    private String stacja;
    private LocalDateTime localDateTime;
    private float temperatura;
    private float predkosc_wiatru;
    private int kierunek_wiatru;
    private float wilgotnosc_wzgledna;
    private float suma_opadu;
    private float cisnienie;

    public SynopticStation(SynopticStationBuilder synopticStationBuilder) {
        this.id_stacji= synopticStationBuilder.id_stacji;
        this.stacja = synopticStationBuilder.stacja;
        this.localDateTime= LocalDateTime.of(synopticStationBuilder.data_pomiaru, synopticStationBuilder.godzina_pomiaru);
        this.temperatura = synopticStationBuilder.temperatura;
        this.predkosc_wiatru = synopticStationBuilder.predkosc_wiatru;
        this.kierunek_wiatru = synopticStationBuilder.kierunek_wiatru;
        this.wilgotnosc_wzgledna = synopticStationBuilder.wilgotnosc_wzgledna;
        this.suma_opadu = synopticStationBuilder.suma_opadu;
        this.cisnienie = synopticStationBuilder.cisnienie;
    }

    public static class SynopticStationBuilder {
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

        public SynopticStationBuilder setId_stacji(Long id_stacji) {
            this.id_stacji = id_stacji;
            return this;
        }

        public SynopticStationBuilder setStacja(String stacja) {
            this.stacja = stacja;
            return this;
        }

        public SynopticStationBuilder setData_pomiaru(LocalDate data_pomiaru) {
            this.data_pomiaru = data_pomiaru;
            return this;
        }

        public SynopticStationBuilder setGodzina_pomiaru(int godzina_pomiaru) {
            this.godzina_pomiaru = LocalTime.of(godzina_pomiaru,0,0,0);
            return this;
        }

        public SynopticStationBuilder setTemperatura(float temperatura) {
            this.temperatura = temperatura;
            return this;
        }

        public SynopticStationBuilder setPredkosc_wiatru(float predkosc_wiatru) {
            this.predkosc_wiatru = predkosc_wiatru;
            return this;
        }

        public SynopticStationBuilder setKierunek_wiatru(int kierunek_wiatru) {
            this.kierunek_wiatru = kierunek_wiatru;
            return this;
        }

        public SynopticStationBuilder setWilgotnosc_wzgledna(float wilgotnosc_wzgledna) {
            this.wilgotnosc_wzgledna = wilgotnosc_wzgledna;
            return this;
        }

        public SynopticStationBuilder setSuma_opadu(float suma_opadu) {
            this.suma_opadu = suma_opadu;
            return this;
        }

        public SynopticStationBuilder setCisnienie(float cisnienie) {
            this.cisnienie = cisnienie;
            return this;
        }

        public SynopticStation build() {
            return new SynopticStation(this);
        }
    }
}
