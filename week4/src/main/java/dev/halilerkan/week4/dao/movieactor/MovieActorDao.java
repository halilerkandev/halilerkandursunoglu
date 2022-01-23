package dev.halilerkan.week4.dao.movieactor;

import dev.halilerkan.week4.dao.person.PersonEntity;

import java.util.List;

public interface MovieActorDao {

    void addActor(Long movieId, Long actorId);

    List<PersonEntity> fetchCast(Long movieId);

}
