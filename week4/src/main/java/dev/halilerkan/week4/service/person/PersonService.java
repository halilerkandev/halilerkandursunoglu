package dev.halilerkan.week4.service.person;

import dev.halilerkan.week4.service.movie.Movie;

import java.util.List;

public interface PersonService {

    Long create(Person person);

    List<Person> fetchAll();

    Person fetchById(Long id);

    Long update(Long id, Person person);

    void delete(Long id);

    List<Movie> directedMovies(Long id);

    List<Movie> playedMovies(Long id);

}
