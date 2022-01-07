package dev.halilerkan.movie.mapper;

import dev.halilerkan.movie.dto.director.DirectorCreateDTO;
import dev.halilerkan.movie.dto.director.DirectorResponseDTO;
import dev.halilerkan.movie.dto.director.DirectorUpdateDTO;
import dev.halilerkan.movie.entity.DirectorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface DirectorMapper {
    DirectorEntity mapFromDirectorCreateDTOToDirectorEntity(DirectorCreateDTO directorCreateDTO);

    DirectorResponseDTO mapFromDirectorEntityToDirectorResponseDTO(DirectorEntity directorEntity);

    void mapFromDirectorUpdateDTOToDirectorEntity(
            DirectorUpdateDTO directorUpdateDTO,
            @MappingTarget DirectorEntity directorEntity
    );
}
