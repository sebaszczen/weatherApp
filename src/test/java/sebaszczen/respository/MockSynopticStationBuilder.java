package sebaszczen.respository;

import sebaszczen.domain.SynopticStation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MockSynopticStationBuilder {

    public List getSynopticStationBuilderList() {
        List list = new ArrayList();

        SynopticStation.SynopticStationBuilder synopticStationBuilder = new SynopticStation.SynopticStationBuilder();
        synopticStationBuilder.setCisnienie(1001).setData_pomiaru(LocalDate.of(2018,8,23))
                .setGodzina_pomiaru(11).setId_stacji(22L).setKierunek_wiatru(23).setPredkosc_wiatru(40)
                .setStacja("warszawa").setSuma_opadu(223).setTemperatura(20).setWilgotnosc_wzgledna(23);

        SynopticStation.SynopticStationBuilder synopticStationBuilder2 = new SynopticStation.SynopticStationBuilder();
        synopticStationBuilder2.setCisnienie(1001).setData_pomiaru(LocalDate.of(2018,8,23))
                .setGodzina_pomiaru(11).setId_stacji(22L).setKierunek_wiatru(23).setPredkosc_wiatru(40)
                .setStacja("warszawa").setSuma_opadu(223).setTemperatura(20).setWilgotnosc_wzgledna(23);

        list.add(synopticStationBuilder);
        list.add(synopticStationBuilder2);
        return list;
    }
}
