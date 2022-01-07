package dev.halilerkan.movie.mapper;

import dev.halilerkan.movie.dto.movie.MovieCreateDTO;
import dev.halilerkan.movie.dto.movie.MovieResponseDTO;
import dev.halilerkan.movie.dto.movie.MovieUpdateDTO;
import dev.halilerkan.movie.entity.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface MovieMapper {
    MovieEntity mapFromMovieCreateDTOToMovieEntity(MovieCreateDTO movieCreateDTO);

    MovieResponseDTO mapFromMovieEntityToMovieResponseDTO(MovieEntity movieEntity);

    void mapFromMovieUpdateDTOToMovieEntity(MovieUpdateDTO movieUpdateDTO, @MappingTarget MovieEntity movieEntity);
}
