package sebaszczen.respository;

import sebaszczen.domain.SynopticStation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MockSynopticStationDto {

    public List<SynopticStation.SynopticStationDto> getSynopticStationDtoList() {
        List <SynopticStation.SynopticStationDto>  list = new ArrayList();

        SynopticStation.SynopticStationDto synopticStationDto = new SynopticStation.SynopticStationDto();
        synopticStationDto.setCisnienie(1001).setData_pomiaru(LocalDate.of(2018,8,23))
                .setGodzina_pomiaru(11).setId_stacji(22L).setKierunek_wiatru(23).setPredkosc_wiatru(40)
                .setStacja("mock").setSuma_opadu(223).setTemperatura(20).setWilgotnosc_wzgledna(23);

        SynopticStation.SynopticStationDto synopticStationDto2 = new SynopticStation.SynopticStationDto();
        synopticStationDto2.setCisnienie(1001).setData_pomiaru(LocalDate.of(2018,8,23))
                .setGodzina_pomiaru(11).setId_stacji(22L).setKierunek_wiatru(23).setPredkosc_wiatru(40)
                .setStacja("mock").setSuma_opadu(223).setTemperatura(20).setWilgotnosc_wzgledna(23);

        list.add(synopticStationDto);
        list.add(synopticStationDto2);
        return list;
    }
}
