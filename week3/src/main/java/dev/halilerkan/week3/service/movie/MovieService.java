package dev.halilerkan.week3.service.movie;

import dev.halilerkan.week3.service.person.Person;

import java.util.List;

public interface MovieService {

    Long create(Movie movie);

    List<Movie> fetchAll();

    Movie fetchById(Long id);

    Long update(Long id, Movie movie);

    void delete(Long id);

    void setDirector(Long movieId, Long directorId);

    void addActor(Long movieId, Long actorId);

    List<Person> fetchCast(Long movieId);

}
