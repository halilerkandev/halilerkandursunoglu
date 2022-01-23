package dev.halilerkan.week4.dao.movieactor;

import dev.halilerkan.week4.common.exception.NotFoundException;
import dev.halilerkan.week4.dao.movie.MovieEntity;
import dev.halilerkan.week4.dao.movie.MovieJpaRepository;
import dev.halilerkan.week4.dao.person.PersonEntity;
import dev.halilerkan.week4.dao.person.PersonJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieActorDaoImpl implements MovieActorDao {

    private final MovieActorJpaRepository movieActorJpaRepository;
    private final MovieJpaRepository movieJpaRepository;
    private final PersonJpaRepository personJpaRepository;

    @Override
    public void addActor(Long movieId, Long actorId) {
        Optional<MovieActorEntity> movieActorEntity = movieActorJpaRepository
                                                        .findByActor_IdAndMovie_Id(actorId, movieId);
        if(movieActorEntity.isEmpty()) {
            MovieEntity movieEntity = movieJpaRepository.findById(movieId)
                    .orElseThrow(NotFoundException.withSupplier());
            PersonEntity personEntity = personJpaRepository.findById(actorId)
                    .orElseThrow(NotFoundException.withSupplier());
            movieActorJpaRepository.save(MovieActorEntity.builder()
                            .movie(movieEntity)
                            .actor(personEntity)
                            .build());
        }
    }

    @Override
    public List<PersonEntity> fetchCast(Long movieId) {
        MovieEntity movieEntity = movieJpaRepository.findById(movieId)
                .orElseThrow(NotFoundException.withSupplier());
        List<MovieActorEntity> movieActorEntityList = movieActorJpaRepository.findByMovie_Id(movieEntity.getId());
        return movieActorEntityList.stream()
                .map(MovieActorEntity::getActor)
                .collect(Collectors.toList());
    }
}