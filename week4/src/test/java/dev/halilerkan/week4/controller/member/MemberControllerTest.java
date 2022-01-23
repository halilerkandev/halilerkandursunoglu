package dev.halilerkan.week4.controller.member;

import dev.halilerkan.week4.BaseIntegrationTest;
import dev.halilerkan.week4.controller.member.request.MemberCreateRequest;
import dev.halilerkan.week4.controller.member.response.MemberCreateResponse;
import dev.halilerkan.week4.dao.member.MemberEntity;
import dev.halilerkan.week4.dao.member.MemberJpaRepository;
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

class MemberControllerTest extends BaseIntegrationTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void should_create_member() {
        //given
        MemberCreateRequest request = new MemberCreateRequest();
        request.setFirstName("firstname");
        request.setLastName("lastname");

        //when
        ResponseEntity<MemberCreateResponse> response =
                testRestTemplate.postForEntity("/api/members", request, MemberCreateResponse.class);

        //then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertFalse(Objects.isNull(response.getBody()));
        assertFalse(Objects.isNull(response.getBody().getId()));

        //validate movie
        Optional<MemberEntity> member = memberJpaRepository.findById(response.getBody().getId());
        assertThat(member).isPresent();
        assertThat(member.get().getFirstName()).isEqualTo("firstname");
        assertThat(member.get().getLastName()).isEqualTo("lastname");

    }
}