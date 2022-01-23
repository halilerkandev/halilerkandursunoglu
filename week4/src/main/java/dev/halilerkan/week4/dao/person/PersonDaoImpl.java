package dev.halilerkan.week4.dao.person;

import dev.halilerkan.week4.common.exception.NoContentException;
import dev.halilerkan.week4.common.exception.NotFoundException;
import dev.halilerkan.week4.dao.movie.MovieEntity;
import dev.halilerkan.week4.dao.movieactor.MovieActorEntity;
import dev.halilerkan.week4.dao.movieactor.MovieActorJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonDaoImpl implements PersonDao {

    private final PersonJpaRepository personJpaRepository;
    private final MovieActorJpaRepository movieActorJpaRepository;

    @Override
    public Long create(PersonEntity entity) {
        return personJpaRepository.save(entity).getId();
    }

    @Override
    public List<PersonEntity> fetchAll() {
        return personJpaRepository.findAll();
    }

    @Override
    public PersonEntity fetchById(Long id) {
        return personJpaRepository
                .findById(id)
                .orElseThrow(NotFoundException.withSupplier());
    }

    @Override
    public Long update(Long id, PersonEntity entity) {
        PersonEntity personEntity = personJpaRepository.findById(id).orElseThrow(NotFoundException.withSupplier());
        personEntity.setUpdatedDate(Instant.now());
        personEntity.setFirstName(entity.getFirstName());
        personEntity.setLastName(entity.getLastName());
        personJpaRepository.save(personEntity);
        return personEntity.getId();
    }

    @Override
    public void delete(Long id) {
        PersonEntity personEntity = personJpaRepository.findById(id).orElseThrow(NoContentException.withSupplier());
        personJpaRepository.deleteById(personEntity.getId());
    }

    @Override
    public List<MovieEntity> directedMovies(Long id) {
        PersonEntity personEntity = personJpaRepository.findById(id).orElseThrow(NoContentException.withSupplier());
        return personEntity.getDirectedMovies();
    }

    @Override
    public List<MovieEntity> playedMovies(Long id) {
        List<MovieActorEntity> movieActorEntityList = movieActorJpaRepository.findByActor_Id(id);
        return movieActorEntityList.stream()
                .map(MovieActorEntity::getMovie)
                .collect(Collectors.toList());
    }

}
