package dev.halilerkan.movie.service.actor;

import dev.halilerkan.movie.dto.actor.ActorCreateDTO;
import dev.halilerkan.movie.dto.actor.ActorResponseDTO;
import dev.halilerkan.movie.dto.actor.ActorUpdateDTO;
import dev.halilerkan.movie.entity.ActorEntity;
import dev.halilerkan.movie.exception.NoContentException;

import java.util.List;

public interface ActorService {
    ActorResponseDTO createActor(ActorCreateDTO actorCreateDTO);

    List<ActorResponseDTO> fetchActorList();

    ActorEntity fetchActorById(Long actorId) throws NoContentException;

    ActorResponseDTO updateActorById(Long actorId, ActorUpdateDTO actorUpdateDTO) throws NoContentException;

    void deleteActorById(Long actorId) throws NoContentException;
}
