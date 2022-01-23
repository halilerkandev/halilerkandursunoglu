package dev.halilerkan.week4.dao.movieactor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieActorJpaRepository extends JpaRepository<MovieActorEntity, Long> {

    Optional<MovieActorEntity> findByActor_IdAndMovie_Id(Long actorId, Long movieId);

    List<MovieActorEntity> findByActor_Id(Long actorId);

    List<MovieActorEntity> findByMovie_Id(Long movieId);

}
