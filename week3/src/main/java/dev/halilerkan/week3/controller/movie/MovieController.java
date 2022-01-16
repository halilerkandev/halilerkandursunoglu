package dev.halilerkan.week3.controller.movie;

import dev.halilerkan.week3.controller.movie.request.AddActorRequest;
import dev.halilerkan.week3.controller.movie.request.MovieCreateRequest;
import dev.halilerkan.week3.controller.movie.request.MovieUpdateRequest;
import dev.halilerkan.week3.controller.movie.request.SetDirectorRequest;
import dev.halilerkan.week3.controller.movie.response.MovieCreateResponse;
import dev.halilerkan.week3.controller.movie.response.MovieDefaultResponse;
import dev.halilerkan.week3.controller.movie.response.MovieUpdateResponse;
import dev.halilerkan.week3.controller.person.response.PersonDefaultResponse;
import dev.halilerkan.week3.service.movie.Movie;
import dev.halilerkan.week3.service.movie.MovieService;
import dev.halilerkan.week3.service.person.Person;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
@Tag(name = "Movies")
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    @Operation(summary = "Create movie")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Movie created"
            )
    })
    public ResponseEntity<MovieCreateResponse> create(
            @RequestBody @Valid MovieCreateRequest movieCreateRequest
    ) {
        Movie movie = movieCreateRequest.toMovie();
        Long movieId = movieService.create(movie);
        MovieCreateResponse movieCreateResponse = MovieCreateResponse.valueOf(movieId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(movieCreateResponse);
    }

    @GetMapping
    @Operation(summary = "Fetch movie list")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Movie list fetched"
            )
    })
    public ResponseEntity<List<MovieDefaultResponse>> fetchAll() {
         List<Movie> movieList = movieService.fetchAll();
         return ResponseEntity
                 .ok()
                 .body(movieList
                         .stream()
                         .map(MovieDefaultResponse::valueOf)
                         .collect(Collectors.toList()));
    }

    @GetMapping("/{movieId}")
    @Operation(summary = "Fetch movie by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Movie fetched"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content()
            )
    })
    public ResponseEntity<MovieDefaultResponse> fetchById(
            @PathVariable Long movieId
    ) {
        Movie movie = movieService.fetchById(movieId);
        return ResponseEntity.ok(MovieDefaultResponse.valueOf(movie));
    }

    @PutMapping("/{movieId}")
    @Operation(summary = "Update movie by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Movie updated"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content()
            )
    })
    public ResponseEntity<MovieUpdateResponse> update(
            @PathVariable Long movieId,
            @RequestBody @Valid MovieUpdateRequest movieUpdateRequest
    ) {
        Movie movie = movieUpdateRequest.toMovie();
        Long id = movieService.update(movieId, movie);
        MovieUpdateResponse movieUpdateResponse = MovieUpdateResponse.valueOf(id);
        return ResponseEntity.ok(movieUpdateResponse);
    }

    @DeleteMapping("/{movieId}")
    @Operation(summary = "Delete movie by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            )
    })
    public ResponseEntity<Void> deleteById(
            @PathVariable Long movieId
    ) {
        movieService.delete(movieId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{movieId}/director")
    @Operation(summary = "Set director to movie")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content()
            )
    })
    public ResponseEntity<Void> setDirector(
            @PathVariable Long movieId,
            @RequestBody @Valid SetDirectorRequest request
    ) {
        movieService.setDirector(movieId, request.getDirectorId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{movieId}/cast")
    @Operation(summary = "Add actor to movie")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content()
            )
    })
    public ResponseEntity<Void> addActor(
            @PathVariable Long movieId,
            @RequestBody @Valid AddActorRequest request
    ) {
        movieService.addActor(movieId, request.getActorId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{movieId}/cast")
    @Operation(summary = "Fetch cast by movie id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cast fetched",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content()
            )
    })
    public ResponseEntity<List<PersonDefaultResponse>> fetchCast(
            @PathVariable Long movieId
    ) {
        List<Person> people = movieService.fetchCast(movieId);
        return ResponseEntity.ok(
                people.stream()
                        .map(PersonDefaultResponse::valueOf)
                        .collect(Collectors.toList()));
    }

}
