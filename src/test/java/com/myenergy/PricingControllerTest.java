package com.myenergy;

import com.myenergy.dto.PricePlanComparisonDto;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PricingControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCompare(){
        webTestClient.get()
                .uri("/my-energy/price-plan/compare/smart-meter-2")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(PricePlanComparisonDto.class);
    }

    @Test
    void testCompareNotFound(){
        webTestClient.get()
                .uri("/my-energy/price-plan/compare/dummy")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    void testRecommendations(){
        webTestClient.get()
                .uri("/my-energy/price-plan/recommendations/smart-meter-2")
                .exchange()
                .expectStatus()
                .isOk();
    }
}
