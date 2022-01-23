package dev.halilerkan.week4.dao.movie;

import dev.halilerkan.week4.common.exception.NoContentException;
import dev.halilerkan.week4.common.exception.NotFoundException;
import dev.halilerkan.week4.dao.person.PersonEntity;
import dev.halilerkan.week4.dao.person.PersonJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieDaoImpl implements MovieDao {

    private final MovieJpaRepository movieJpaRepository;
    private final PersonJpaRepository personJpaRepository;

    @Override
    public Long create(MovieEntity entity) {
        return movieJpaRepository.save(entity).getId();
    }

    @Override
    public List<MovieEntity> fetchAll() {
        return movieJpaRepository.findAll();
    }

    @Override
    public MovieEntity fetchById(Long id) {
        return movieJpaRepository
                .findById(id)
                .orElseThrow(NotFoundException.withSupplier());
    }

    @Override
    public Long update(Long id, MovieEntity entity) {
        MovieEntity movieEntity = movieJpaRepository.findById(id).orElseThrow(NotFoundException.withSupplier());
        movieEntity.setUpdatedDate(Instant.now());
        movieEntity.setName(entity.getName());
        movieEntity.setGenre(entity.getGenre());
        movieEntity.setReleaseYear(entity.getReleaseYear());
        movieJpaRepository.save(movieEntity);
        return movieEntity.getId();
    }

    @Override
    public void delete(Long id) {
        MovieEntity movieEntity = movieJpaRepository.findById(id).orElseThrow(NoContentException.withSupplier());
        movieJpaRepository.deleteById(movieEntity.getId());
    }

    @Override
    public void setDirector(Long movieId, Long directorId) {
        MovieEntity movieEntity = movieJpaRepository.findById(movieId).orElseThrow(NoContentException.withSupplier());
        PersonEntity personEntity = personJpaRepository.findById(directorId).orElseThrow(NoContentException.withSupplier());
        movieEntity.setDirector(personEntity);
        movieJpaRepository.save(movieEntity);
    }

}
