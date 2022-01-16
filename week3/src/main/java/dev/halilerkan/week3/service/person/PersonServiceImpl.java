package dev.halilerkan.week3.service.person;

import dev.halilerkan.week3.dao.movie.MovieEntity;
import dev.halilerkan.week3.dao.person.PersonDao;
import dev.halilerkan.week3.dao.person.PersonEntity;
import dev.halilerkan.week3.service.movie.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao;
    //private final RedisTemplate<Long, Movie> movieRedisTemplate;

    @Override
    public Long create(Person person) {
        PersonEntity entity = person.toPersonEntity();
        return personDao.create(entity);
    }

    @Override
    public List<Person> fetchAll() {
        List<PersonEntity> entityList = personDao.fetchAll();
        return entityList
                .stream()
                .map(Person::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public Person fetchById(Long id) {
        PersonEntity entity = personDao.fetchById(id);
        return Person.valueOf(entity);
    }

    @Override
    public Long update(Long id, Person person) {
        PersonEntity entity = person.toPersonEntity();
        return personDao.update(id, entity);
    }

    @Override
    public void delete(Long id) {
        personDao.delete(id);
    }

    @Override
    public List<Movie> directedMovies(Long id) {
         List<MovieEntity> entityList = personDao.directedMovies(id);
         return entityList.stream()
                 .map(Movie::valueOf)
                 .collect(Collectors.toList());
    }

    @Override
    public List<Movie> playedMovies(Long id) {
        List<MovieEntity> entityList = personDao.playedMovies(id);
        return entityList.stream()
                .map(Movie::valueOf)
                .collect(Collectors.toList());
    }

}
