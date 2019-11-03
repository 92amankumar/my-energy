package com.myenergy;

import com.myenergy.dto.ElectricityReadingsDto;
import com.myenergy.dto.GetElectricityReadingsDto;
import com.myenergy.dto.ReadingsDto;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReadingsControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testGetAllReadingsFromSarahMeterId(){
        webTestClient.get()
                .uri("/my-energy/readings/smart-meter-0")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(GetElectricityReadingsDto.class);
    }

    @Test
    void testGetAllReadingsFromNoMeterId(){
        webTestClient.get()
                .uri("/my-energy/readings/dummy")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    void testSaveReadings(){
        ReadingsDto readingsDto = new ReadingsDto();
        readingsDto.setSmartMeterId("smart-meter-1");
        List<ElectricityReadingsDto> electricityReadingsDtos = new ArrayList<>();
        ElectricityReadingsDto electricityReadingsDto = new ElectricityReadingsDto();
        electricityReadingsDto.setReading(BigDecimal.ONE);
        electricityReadingsDto.setTime(new Date().getTime());
        electricityReadingsDtos.add(electricityReadingsDto);
        readingsDto.setElectricityReadings(electricityReadingsDtos);
        webTestClient.post()
                .uri("/my-energy/readings")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(readingsDto), ReadingsDto.class)
                .exchange().expectStatus().isOk();
    }

    @Test
    void testSaveReadingsBadRequest(){
        ReadingsDto readingsDto = new ReadingsDto();
        readingsDto.setSmartMeterId("smart-meter-1");
        List<ElectricityReadingsDto> electricityReadingsDtos = new ArrayList<>();
        ElectricityReadingsDto electricityReadingsDto = new ElectricityReadingsDto();
        electricityReadingsDto.setReading(null);
        electricityReadingsDto.setTime(new Date().getTime());
        electricityReadingsDtos.add(electricityReadingsDto);
        readingsDto.setElectricityReadings(electricityReadingsDtos);
        webTestClient.post()
                .uri("/my-energy/readings")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(readingsDto), ReadingsDto.class)
                .exchange().expectStatus().isBadRequest();
    }
}
