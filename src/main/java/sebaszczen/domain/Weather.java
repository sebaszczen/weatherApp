package sebaszczen.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Calendar;

@Entity
public class Weather {
    @Id
    @GeneratedValue
    private Long id_stacji;
    private String stacja;
    private Calendar data_pomiaru;
    private String hour;
    private float temperatura;
    private float predkosc_wiatru;
    private int kierunek_wiatru;
    private float wilgotnosc_wzgledna;
    private float suma_opadu;
    private float cisnienie;

    public Weather(WeatherBuilder weatherBuilder) {
        this.stacja = weatherBuilder.stacja;
        this.data_pomiaru = weatherBuilder.data_pomiaru;
        this.hour = weatherBuilder.hour;
        this.temperatura = weatherBuilder.temperatura;
        this.predkosc_wiatru = weatherBuilder.predkosc_wiatru;
        this.kierunek_wiatru = weatherBuilder.kierunek_wiatru;
        this.wilgotnosc_wzgledna = weatherBuilder.wilgotnosc_wzgledna;
        this.suma_opadu = weatherBuilder.suma_opadu;
        this.cisnienie = weatherBuilder.cisnienie;
    }

    public Long getId_stacji() {
        return id_stacji;
    }

    public String getStacja() {
        return stacja;
    }

    public Calendar getData_pomiaru() {
        return data_pomiaru;
    }

    public String getHour() {
        return hour;
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

    protected static class WeatherBuilder {
        private String stacja;
        private Calendar data_pomiaru;
        private String hour;
        private float temperatura;
        private float predkosc_wiatru;
        private int kierunek_wiatru;
        private float wilgotnosc_wzgledna;
        private float suma_opadu;
        private float cisnienie;

        public WeatherBuilder setStacja(String stacja) {
            this.stacja = stacja;
            return this;
        }

        public WeatherBuilder setData_pomiaru(Calendar data_pomiaru) {
            this.data_pomiaru = data_pomiaru;
            return this;
        }

        public WeatherBuilder setHour(String hour) {
            this.hour = hour;
            return this;
        }

        public WeatherBuilder setTemperatura(float temperatura) {
            this.temperatura = temperatura;
            return this;
        }

        public WeatherBuilder setPredkosc_wiatru(float predkosc_wiatru) {
            this.predkosc_wiatru = predkosc_wiatru;
            return this;
        }

        public WeatherBuilder setKierunek_wiatru(int kierunek_wiatru) {
            this.kierunek_wiatru = kierunek_wiatru;
            return this;
        }

        public WeatherBuilder setWilgotnosc_wzgledna(float wilgotnosc_wzgledna) {
            this.wilgotnosc_wzgledna = wilgotnosc_wzgledna;
            return this;
        }

        public WeatherBuilder setSuma_opadu(float suma_opadu) {
            this.suma_opadu = suma_opadu;
            return this;
        }

        public WeatherBuilder setCisnienie(float cisnienie) {
            this.cisnienie = cisnienie;
            return this;
        }

        public Weather build() {
            return new Weather(this);
        }
    }
}
