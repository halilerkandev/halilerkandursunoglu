package dev.halilerkan.week4.controller.person;

import dev.halilerkan.week4.BaseIntegrationTest;
import dev.halilerkan.week4.controller.person.request.PersonCreateRequest;
import dev.halilerkan.week4.controller.person.response.PersonCreateResponse;
import dev.halilerkan.week4.dao.person.PersonEntity;
import dev.halilerkan.week4.dao.person.PersonJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PersonControllerTest extends BaseIntegrationTest {

    @Autowired
    PersonJpaRepository personJpaRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void should_create_person() {
        //given
        PersonCreateRequest request = new PersonCreateRequest();
        request.setFirstName("firstname");
        request.setLastName("lastname");

        //when
        ResponseEntity<PersonCreateResponse> response =
                testRestTemplate.postForEntity("/api/people", request, PersonCreateResponse.class);

        //then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertFalse(Objects.isNull(response.getBody()));
        assertFalse(Objects.isNull(response.getBody().getId()));

        //validate movie
        Optional<PersonEntity> person = personJpaRepository.findById(response.getBody().getId());
        assertThat(person).isPresent();
        assertThat(person.get().getFirstName()).isEqualTo("firstname");
        assertThat(person.get().getLastName()).isEqualTo("lastname");

    }
}