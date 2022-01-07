package dev.halilerkan.movie.service.director;

import dev.halilerkan.movie.dto.director.DirectorCreateDTO;
import dev.halilerkan.movie.dto.director.DirectorResponseDTO;
import dev.halilerkan.movie.dto.director.DirectorUpdateDTO;
import dev.halilerkan.movie.entity.DirectorEntity;
import dev.halilerkan.movie.exception.NoContentException;
import dev.halilerkan.movie.mapper.DirectorMapper;
import dev.halilerkan.movie.repository.DirectorRepository;
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
public class DirectorServiceImpl implements DirectorService {
    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    @Override
    public DirectorResponseDTO createDirector(DirectorCreateDTO directorCreateDTO) {
        DirectorEntity directorEntity = directorMapper
                .mapFromDirectorCreateDTOToDirectorEntity(directorCreateDTO);

        directorRepository.save(directorEntity);

        return directorMapper.mapFromDirectorEntityToDirectorResponseDTO(directorEntity);
    }

    @Override
    public List<DirectorResponseDTO> fetchDirectorList() {
        return directorRepository.findAll()
                .stream()
                .map(directorMapper::mapFromDirectorEntityToDirectorResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DirectorEntity fetchDirectorById(Long directorId) throws NoContentException {
        return directorRepository
                .findById(directorId)
                .orElseThrow(NoContentException.buildWithSupplier());
    }

    @Override
    public DirectorResponseDTO updateDirectorById(
            Long directorId,
            DirectorUpdateDTO directorUpdateDTO
    ) throws NoContentException {
        DirectorEntity directorEntity = directorRepository
                .findById(directorId)
                .orElseThrow(NoContentException.buildWithSupplier());

        directorMapper.mapFromDirectorUpdateDTOToDirectorEntity(directorUpdateDTO, directorEntity);

        directorEntity.setLastModifiedDate(Instant.now());

        directorRepository.save(directorEntity);

        return directorMapper.mapFromDirectorEntityToDirectorResponseDTO(directorEntity);
    }

    @Override
    public void deleteDirectorById(Long directorId) throws NoContentException {
        DirectorEntity directorEntity = directorRepository
                .findById(directorId)
                .orElseThrow(NoContentException.buildWithSupplier());

        directorRepository.deleteById(directorEntity.getDirectorId());
    }
}
