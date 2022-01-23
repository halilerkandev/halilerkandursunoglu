package dev.halilerkan.week4.controller.rate;

import dev.halilerkan.week4.BaseIntegrationTest;
import dev.halilerkan.week4.controller.rate.request.RateRequest;
import dev.halilerkan.week4.controller.rate.response.RateResponse;
import dev.halilerkan.week4.dao.rate.RateEntity;
import dev.halilerkan.week4.dao.rate.RateJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

class RateControllerTest extends BaseIntegrationTest {

    @Autowired
    RateJpaRepository rateJpaRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Sql(scripts = "/rate-create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_create_rate() {
        //given
        RateRequest request = new RateRequest();
        request.setMemberId(1001L);
        request.setMovieId(2001L);
        request.setPoint(3);

        //when
        ResponseEntity<RateResponse> response =
                testRestTemplate.postForEntity("/api/rates", request, RateResponse.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNull();

        //validate movie
        Optional<RateEntity> rate = rateJpaRepository.findByMemberIdAndMovieId(1001L, 2001L);
        assertThat(rate).isPresent();
        assertThat(rate.get().getMember().getFirstName()).isEqualTo("firstname");
        assertThat(rate.get().getMovie().getName()).isEqualTo("movie");
        assertThat(rate.get().getPoint()).isEqualTo(3);

    }

    @Test
    @Sql(scripts = "/rate-fetch-all.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_fetch_all_rates() {
        //when
        ResponseEntity<RateResponseList> response =
                testRestTemplate.getForEntity("/api/rates?movieId=2001", RateResponseList.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getRates().size()).isEqualTo(2);
    }
}