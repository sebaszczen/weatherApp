package sebaszczen.respository;

import sebaszczen.model.SynopticData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MockSynopticData {

    public List<SynopticData.SynoptiDataDto> getSynopticStationDto() {
        List <SynopticData.SynoptiDataDto>  list = new ArrayList();

        SynopticData.SynoptiDataDto synoptiDataDto = new SynopticData.SynoptiDataDto();
        synoptiDataDto.setCisnienie(1001).setData_pomiaru(LocalDate.of(2018,8,23))
                .setGodzina_pomiaru(11).setId_stacji(22L).setKierunek_wiatru(23).setPredkosc_wiatru(40)
                .setStacja("mock").setSuma_opadu(223).setTemperatura(20).setWilgotnosc_wzgledna(23);

        SynopticData.SynoptiDataDto synoptiDataDto2 = new SynopticData.SynoptiDataDto();
        synoptiDataDto2.setCisnienie(1001).setData_pomiaru(LocalDate.of(2018,8,23))
                .setGodzina_pomiaru(11).setId_stacji(22L).setKierunek_wiatru(23).setPredkosc_wiatru(40)
                .setStacja("mock").setSuma_opadu(223).setTemperatura(20).setWilgotnosc_wzgledna(23);

        list.add(synoptiDataDto);
        list.add(synoptiDataDto2);
        return list;
    }

    public List<SynopticData> getSynopticData() {
        List<SynopticData.SynoptiDataDto> synoptiDataDtoList = getSynopticStationDto();
        return synoptiDataDtoList.parallelStream().map(SynopticData::new).collect(Collectors.toList());
    }
}
