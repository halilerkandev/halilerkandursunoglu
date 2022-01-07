package dev.halilerkan.movie.service.movie;

import dev.halilerkan.movie.dto.movie.MovieCreateDTO;
import dev.halilerkan.movie.dto.movie.MovieResponseDTO;
import dev.halilerkan.movie.dto.movie.MovieUpdateDTO;
import dev.halilerkan.movie.entity.MovieEntity;
import dev.halilerkan.movie.exception.NoContentException;

import java.util.List;

public interface MovieService {
    MovieResponseDTO createMovie(MovieCreateDTO movieCreateDTO);

    List<MovieResponseDTO> fetchMovieList();

    MovieEntity fetchMovieById(Long movieId) throws NoContentException;

    MovieResponseDTO updateMovieById(Long movieId, MovieUpdateDTO movieUpdateDTO) throws NoContentException;

    void deleteMovieById(Long movieId) throws NoContentException;
}
