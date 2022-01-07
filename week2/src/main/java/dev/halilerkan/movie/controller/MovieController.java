package dev.halilerkan.movie.controller;

import dev.halilerkan.movie.dto.actor.AddActorToMovieRequestDTO;
import dev.halilerkan.movie.dto.director.AddDirectorToMovieRequestDTO;
import dev.halilerkan.movie.dto.movie.MovieCreateDTO;
import dev.halilerkan.movie.dto.movie.MovieResponseDTO;
import dev.halilerkan.movie.dto.movie.MovieUpdateDTO;
import dev.halilerkan.movie.entity.ActorEntity;
import dev.halilerkan.movie.entity.DirectorEntity;
import dev.halilerkan.movie.entity.MovieEntity;
import dev.halilerkan.movie.mapper.MovieMapper;
import dev.halilerkan.movie.service.actor.ActorService;
import dev.halilerkan.movie.service.director.DirectorService;
import dev.halilerkan.movie.service.movie.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;
    private final ActorService actorService;
    private final DirectorService directorService;

    @PostMapping("/movie")
    @Operation(summary = "Create movie")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Movie successfully created"
            )
    })
    public ResponseEntity<MovieResponseDTO> createMovie(
            @Valid @RequestBody MovieCreateDTO movieCreateDTO
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(movieService.createMovie(movieCreateDTO));
    }

    @GetMapping("/movies")
    @Operation(summary = "Fetch movie list")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Movie list successfully fetched"
            )
    })
    public ResponseEntity<List<MovieResponseDTO>> fetchMovieList() {
        return ResponseEntity.ok(movieService.fetchMovieList());
    }

    @GetMapping("/movie/{movieId}")
    @Operation(summary = "Fetch movie by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Movie successfully fetched"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            )
    })
    public ResponseEntity<MovieResponseDTO> fetchMovieById(@PathVariable("movieId") Long movieId) {
        MovieEntity movieEntity = movieService.fetchMovieById(movieId);
        return ResponseEntity.ok(movieMapper.mapFromMovieEntityToMovieResponseDTO(movieEntity));
    }

    @PutMapping("/movie/{movieId}")
    @Operation(summary = "Update movie by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Movie successfully updated"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            )
    })
    public ResponseEntity<MovieResponseDTO> updateMovieById(
            @PathVariable("movieId") Long movieId,
            @RequestBody @Valid MovieUpdateDTO movieUpdateDTO
    ) {
        return ResponseEntity.ok(movieService.updateMovieById(movieId, movieUpdateDTO));
    }

    @DeleteMapping("/movie/{movieId}")
    @Operation(summary = "Delete movie by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            )
    })
    public ResponseEntity<Void> deleteMovieById(@PathVariable("movieId") Long movieId) {
        movieService.deleteMovieById(movieId);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/movie/{movieId}/actor")
    @Operation(summary = "Add actor to movie")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            )
    })
    public ResponseEntity<Void> addActorToMovie(
            @PathVariable("movieId") Long movieId,
            @RequestBody @Valid AddActorToMovieRequestDTO addActorToMovieRequestDTO
    ) {
        MovieEntity movieEntity = movieService.fetchMovieById(movieId);
        ActorEntity actorEntity = actorService.fetchActorById(addActorToMovieRequestDTO.getActorId());
        movieEntity.addActor(actorEntity);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/movie/{movieId}/director")
    @Operation(summary = "Add director to movie")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            )
    })
    public ResponseEntity<Void> addDirectorToMovie(
            @PathVariable("movieId") Long movieId,
            @RequestBody @Valid AddDirectorToMovieRequestDTO addDirectorToMovieRequestDTO
    ) {
        MovieEntity movieEntity = movieService.fetchMovieById(movieId);
        DirectorEntity directorEntity = directorService.fetchDirectorById(addDirectorToMovieRequestDTO.getDirectorId());
        movieEntity.setDirector(directorEntity);
        return ResponseEntity.noContent().build();
    }
}

