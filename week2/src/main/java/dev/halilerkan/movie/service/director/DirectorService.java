package dev.halilerkan.movie.service.director;

import dev.halilerkan.movie.dto.director.DirectorCreateDTO;
import dev.halilerkan.movie.dto.director.DirectorResponseDTO;
import dev.halilerkan.movie.dto.director.DirectorUpdateDTO;
import dev.halilerkan.movie.entity.DirectorEntity;
import dev.halilerkan.movie.exception.NoContentException;

import java.util.List;

public interface DirectorService {
    DirectorResponseDTO createDirector(DirectorCreateDTO directorCreateDTO);

    List<DirectorResponseDTO> fetchDirectorList();

    DirectorEntity fetchDirectorById(Long directorId) throws NoContentException;

    DirectorResponseDTO updateDirectorById(
            Long directorId,
            DirectorUpdateDTO directorUpdateDTO
    ) throws NoContentException;

    void deleteDirectorById(Long directorId) throws NoContentException;
}
