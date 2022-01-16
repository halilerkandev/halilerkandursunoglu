package dev.halilerkan.week3.controller.person;

import dev.halilerkan.week3.controller.movie.response.MovieDefaultResponse;
import dev.halilerkan.week3.controller.person.request.PersonCreateRequest;
import dev.halilerkan.week3.controller.person.request.PersonUpdateRequest;
import dev.halilerkan.week3.controller.person.response.PersonCreateResponse;
import dev.halilerkan.week3.controller.person.response.PersonDefaultResponse;
import dev.halilerkan.week3.controller.person.response.PersonUpdateResponse;
import dev.halilerkan.week3.service.movie.Movie;
import dev.halilerkan.week3.service.person.Person;
import dev.halilerkan.week3.service.person.PersonService;
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
@RequestMapping("/api/people")
@RequiredArgsConstructor
@Tag(name = "People")
public class PersonController {

    private final PersonService personService;

    @PostMapping
    @Operation(summary = "Create person")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Person created"
            )
    })
    public ResponseEntity<PersonCreateResponse> create(
            @RequestBody @Valid PersonCreateRequest request
    ) {
        Person person = request.toPerson();
        Long id = personService.create(person);
        PersonCreateResponse response = PersonCreateResponse.valueOf(id);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    @Operation(summary = "Fetch people")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "People fetched"
            )
    })
    public ResponseEntity<List<PersonDefaultResponse>> fetchAll() {
        List<Person> people = personService.fetchAll();
        return ResponseEntity
                .ok()
                .body(people
                        .stream()
                        .map(PersonDefaultResponse::valueOf)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{personId}")
    @Operation(summary = "Fetch person by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Person fetched"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content()
            )
    })
    public ResponseEntity<PersonDefaultResponse> fetchById(
            @PathVariable Long personId
    ) {
        Person person = personService.fetchById(personId);
        return ResponseEntity.ok(PersonDefaultResponse.valueOf(person));
    }

    @PutMapping("/{personId}")
    @Operation(summary = "Update person by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Person updated"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content()
            )
    })
    public ResponseEntity<PersonUpdateResponse> update(
            @PathVariable Long personId,
            @RequestBody @Valid PersonUpdateRequest request
    ) {
        Person person = request.toPerson();
        Long id = personService.update(personId, person);
        PersonUpdateResponse response = PersonUpdateResponse.valueOf(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{personId}")
    @Operation(summary = "Delete person by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            )
    })
    public ResponseEntity<Void> deleteById(
            @PathVariable Long personId
    ) {
        personService.delete(personId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{personId}/directed")
    @Operation(summary = "Fetch directed movies")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Directed movies fetched"
            )
    })
    public ResponseEntity<List<MovieDefaultResponse>> directedMovies(
            @PathVariable Long personId
    ) {
        List<Movie> movieList = personService.directedMovies(personId);
        return ResponseEntity.ok(
                movieList.stream()
                        .map(MovieDefaultResponse::valueOf)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{personId}/played")
    @Operation(summary = "Fetch played movies")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Played movies fetched"
            )
    })
    public ResponseEntity<List<MovieDefaultResponse>> playedMovies(
            @PathVariable Long personId
    ) {
        List<Movie> movieList = personService.playedMovies(personId);
        return ResponseEntity.ok(
                movieList.stream()
                        .map(MovieDefaultResponse::valueOf)
                        .collect(Collectors.toList())
        );
    }
}
