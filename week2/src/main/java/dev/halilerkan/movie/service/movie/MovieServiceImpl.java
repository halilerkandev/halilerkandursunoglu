package dev.halilerkan.movie.service.movie;

import dev.halilerkan.movie.dto.movie.MovieCreateDTO;
import dev.halilerkan.movie.dto.movie.MovieResponseDTO;
import dev.halilerkan.movie.dto.movie.MovieUpdateDTO;
import dev.halilerkan.movie.entity.MovieEntity;
import dev.halilerkan.movie.exception.NoContentException;
import dev.halilerkan.movie.mapper.MovieMapper;
import dev.halilerkan.movie.repository.MovieRepository;
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
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    public MovieResponseDTO createMovie(MovieCreateDTO movieCreateDTO) {
        MovieEntity movieEntity = movieMapper
                .mapFromMovieCreateDTOToMovieEntity(movieCreateDTO);

        movieRepository.save(movieEntity);

        return movieMapper.mapFromMovieEntityToMovieResponseDTO(movieEntity);
    }

    @Override
    public List<MovieResponseDTO> fetchMovieList() {
        return movieRepository.findAll()
                .stream()
                .map(movieMapper::mapFromMovieEntityToMovieResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MovieEntity fetchMovieById(Long movieId) throws NoContentException {
        return  movieRepository
                .findById(movieId)
                .orElseThrow(NoContentException.buildWithSupplier());
    }

    @Override
    public MovieResponseDTO updateMovieById(Long movieId, MovieUpdateDTO movieUpdateDTO) throws NoContentException {
        MovieEntity movieEntity = movieRepository
                .findById(movieId)
                .orElseThrow(NoContentException.buildWithSupplier());

        movieMapper.mapFromMovieUpdateDTOToMovieEntity(movieUpdateDTO, movieEntity);

        movieEntity.setLastModifiedDate(Instant.now());

        movieRepository.save(movieEntity);

        return movieMapper.mapFromMovieEntityToMovieResponseDTO(movieEntity);
    }

    @Override
    public void deleteMovieById(Long movieId) throws NoContentException {
        MovieEntity movieEntity = movieRepository
                .findById(movieId)
                .orElseThrow(NoContentException.buildWithSupplier());

        movieRepository.deleteById(movieEntity.getMovieId());
    }
}
