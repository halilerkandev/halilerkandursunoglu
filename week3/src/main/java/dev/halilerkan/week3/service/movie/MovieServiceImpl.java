package dev.halilerkan.week3.service.movie;

import dev.halilerkan.week3.dao.movie.MovieDao;
import dev.halilerkan.week3.dao.movie.MovieEntity;
import dev.halilerkan.week3.dao.movieactor.MovieActorDao;
import dev.halilerkan.week3.dao.person.PersonEntity;
import dev.halilerkan.week3.service.person.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieDao movieDao;
    private final MovieActorDao movieActorDao;

    @Qualifier("movieRedisTemplate")
    private RedisTemplate<Long, Movie> movieRedisTemplate;

    public MovieServiceImpl(MovieDao movieDao, MovieActorDao movieActorDao) {
        this.movieDao = movieDao;
        this.movieActorDao = movieActorDao;
    }

    @CacheEvict(value = "movies", allEntries = true)
    @Override
    public Long create(Movie movie) {
        MovieEntity entity = movie.toMovieEntity();
        return movieDao.create(entity);
    }

    @Cacheable("movies")
    @Override
    public List<Movie> fetchAll() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        List<MovieEntity> entityList = movieDao.fetchAll();
        return entityList
                .stream()
                .map(Movie::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public Movie fetchById(Long id) {
        MovieEntity entity = movieDao.fetchById(id);
        return Movie.valueOf(entity);
    }

    @Override
    public Long update(Long id, Movie movie) {
        MovieEntity entity = movie.toMovieEntity();
        return movieDao.update(id, entity);
    }

    @Override
    public void delete(Long id) {
        movieDao.delete(id);
    }

    @Override
    public void setDirector(Long movieId, Long directorId) {
        movieDao.setDirector(movieId, directorId);
    }

    @Override
    public void addActor(Long movieId, Long actorId) {
        movieActorDao.addActor(movieId, actorId);
    }

    @Override
    public List<Person> fetchCast(Long movieId) {
        List<PersonEntity> entityList = movieActorDao.fetchCast(movieId);
        return entityList.stream()
                .map(Person::valueOf)
                .collect(Collectors.toList());
    }

}
