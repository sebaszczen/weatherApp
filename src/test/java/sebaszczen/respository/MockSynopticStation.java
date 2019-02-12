package sebaszczen.respository;

import sebaszczen.model.SynopticData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MockSynopticStation {

    public List<SynopticData.SynopticStationDto> getSynopticStationDtoList() {
        List <SynopticData.SynopticStationDto>  list = new ArrayList();

        SynopticData.SynopticStationDto synopticStationDto = new SynopticData.SynopticStationDto();
        synopticStationDto.setCisnienie(1001).setData_pomiaru(LocalDate.of(2018,8,23))
                .setGodzina_pomiaru(11).setId_stacji(22L).setKierunek_wiatru(23).setPredkosc_wiatru(40)
                .setStacja("mock").setSuma_opadu(223).setTemperatura(20).setWilgotnosc_wzgledna(23);

        SynopticData.SynopticStationDto synopticStationDto2 = new SynopticData.SynopticStationDto();
        synopticStationDto2.setCisnienie(1001).setData_pomiaru(LocalDate.of(2018,8,23))
                .setGodzina_pomiaru(11).setId_stacji(22L).setKierunek_wiatru(23).setPredkosc_wiatru(40)
                .setStacja("mock").setSuma_opadu(223).setTemperatura(20).setWilgotnosc_wzgledna(23);

        list.add(synopticStationDto);
        list.add(synopticStationDto2);
        return list;
    }

    public List<SynopticData> getSynopticStationList() {
        List<SynopticData.SynopticStationDto> synopticStationDtoList = getSynopticStationDtoList();
        return synopticStationDtoList.parallelStream().map(SynopticData::new).collect(Collectors.toList());
    }
}
