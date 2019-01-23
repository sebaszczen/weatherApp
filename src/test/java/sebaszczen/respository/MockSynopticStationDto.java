package sebaszczen.respository;

import sebaszczen.model.SynopticStation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<SynopticStation> getSynopticStationList() {
        List<SynopticStation.SynopticStationDto> synopticStationDtoList = getSynopticStationDtoList();
        return synopticStationDtoList.parallelStream().map(SynopticStation::new).collect(Collectors.toList());
    }
}
