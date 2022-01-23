package dev.halilerkan.week4.controller.movie;

import dev.halilerkan.week4.BaseIntegrationTest;
import dev.halilerkan.week4.common.enumeration.MovieGenre;
import dev.halilerkan.week4.controller.movie.request.MovieCreateRequest;
import dev.halilerkan.week4.controller.movie.response.MovieCreateResponse;
import dev.halilerkan.week4.dao.movie.MovieEntity;
import dev.halilerkan.week4.dao.movie.MovieJpaRepository;
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

class MovieControllerTest extends BaseIntegrationTest {

    @Autowired
    PersonJpaRepository personJpaRepository;

    @Autowired
    MovieJpaRepository movieJpaRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void should_create_movie() {
        //given
        MovieCreateRequest request = new MovieCreateRequest();
        request.setGenre(MovieGenre.COMEDY);
        request.setName("Test 1");
        request.setReleaseYear(2010);

        //when
        ResponseEntity<MovieCreateResponse> response =
                testRestTemplate.postForEntity("/api/movies", request, MovieCreateResponse.class);

        //then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertFalse(Objects.isNull(response.getBody()));
        assertFalse(Objects.isNull(response.getBody().getId()));

        //validate movie
        Optional<MovieEntity> movie = movieJpaRepository.findById(response.getBody().getId());
        assertThat(movie).isPresent();
        assertThat(movie.get().getName()).isEqualTo("Test 1");
        assertThat(movie.get().getGenre()).isEqualTo(MovieGenre.COMEDY);
        assertThat(movie.get().getReleaseYear()).isEqualTo(2010);

    }
}