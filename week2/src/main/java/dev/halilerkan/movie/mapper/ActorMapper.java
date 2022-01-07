package dev.halilerkan.movie.mapper;

import dev.halilerkan.movie.dto.actor.ActorCreateDTO;
import dev.halilerkan.movie.dto.actor.ActorResponseDTO;
import dev.halilerkan.movie.dto.actor.ActorUpdateDTO;
import dev.halilerkan.movie.entity.ActorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface ActorMapper {
    ActorEntity mapFromActorCreateDTOToActorEntity(ActorCreateDTO actorCreateDTO);

    ActorResponseDTO mapFromActorEntityToActorResponseDTO(ActorEntity actorEntity);

    void mapFromActorUpdateDTOToActorEntity(ActorUpdateDTO actorUpdateDTO, @MappingTarget ActorEntity actorEntity);
}
