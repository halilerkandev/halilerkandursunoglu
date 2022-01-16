package dev.halilerkan.week3.dao.movie;

import java.util.List;

public interface MovieDao {

    Long create(MovieEntity entity);

    List<MovieEntity> fetchAll();

    MovieEntity fetchById(Long id);

    Long update(Long id, MovieEntity entity);

    void delete(Long id);

    void setDirector(Long movieId, Long directorId);

}
