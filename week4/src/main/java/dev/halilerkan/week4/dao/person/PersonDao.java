package dev.halilerkan.week4.dao.person;

import dev.halilerkan.week4.dao.movie.MovieEntity;

import java.util.List;

public interface PersonDao {

    Long create(PersonEntity entity);

    List<PersonEntity> fetchAll();

    PersonEntity fetchById(Long id);

    Long update(Long id, PersonEntity entity);

    void delete(Long id);

    List<MovieEntity> directedMovies(Long id);

    List<MovieEntity> playedMovies(Long id);

}
