package dev.halilerkan.movie.service.actor;

import dev.halilerkan.movie.dto.actor.ActorCreateDTO;
import dev.halilerkan.movie.dto.actor.ActorResponseDTO;
import dev.halilerkan.movie.dto.actor.ActorUpdateDTO;
import dev.halilerkan.movie.entity.ActorEntity;
import dev.halilerkan.movie.exception.NoContentException;
import dev.halilerkan.movie.mapper.ActorMapper;
import dev.halilerkan.movie.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ActorServiceImpl implements ActorService {
    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    @Override
    public ActorResponseDTO createActor(ActorCreateDTO actorCreateDTO) {
        ActorEntity actorEntity = actorMapper
                .mapFromActorCreateDTOToActorEntity(actorCreateDTO);

        actorRepository.save(actorEntity);

        return actorMapper.mapFromActorEntityToActorResponseDTO(actorEntity);
    }

    @Override
    public List<ActorResponseDTO> fetchActorList() {
        return actorRepository.findAll()
                .stream()
                .map(actorMapper::mapFromActorEntityToActorResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ActorEntity fetchActorById(Long actorId) throws NoContentException {
        return actorRepository
                .findById(actorId)
                .orElseThrow(NoContentException.buildWithSupplier());
    }

    @Override
    public ActorResponseDTO updateActorById(Long actorId, ActorUpdateDTO actorUpdateDTO) throws NoContentException {
        ActorEntity actorEntity = actorRepository
                .findById(actorId)
                .orElseThrow(NoContentException.buildWithSupplier());

        actorMapper.mapFromActorUpdateDTOToActorEntity(actorUpdateDTO, actorEntity);

        actorEntity.setLastModifiedDate(Instant.now());

        actorRepository.save(actorEntity);

        return actorMapper.mapFromActorEntityToActorResponseDTO(actorEntity);
    }

    @Override
    public void deleteActorById(Long actorId) {
        ActorEntity actorEntity = actorRepository
                .findById(actorId)
                .orElseThrow(NoContentException.buildWithSupplier());

        actorRepository.deleteById(actorEntity.getActorId());
    }
}
