package dev.halilerkan.movie.controller;

import dev.halilerkan.movie.dto.actor.ActorCreateDTO;
import dev.halilerkan.movie.dto.actor.ActorResponseDTO;
import dev.halilerkan.movie.dto.actor.ActorUpdateDTO;
import dev.halilerkan.movie.mapper.ActorMapper;
import dev.halilerkan.movie.service.actor.ActorService;
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
@Tag(name = "Actors")
public class ActorController {
    private final ActorService actorService;
    private final ActorMapper actorMapper;

    @PostMapping("/actor")
    @Operation(summary = "Create actor")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Actor successfully created"
            )
    })
    public ResponseEntity<ActorResponseDTO> createActor(
            @RequestBody @Valid ActorCreateDTO actorCreateDTO
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(actorService.createActor(actorCreateDTO));
    }

    @GetMapping("/actors")
    @Operation(summary = "Fetch actor list")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Actor list successfully fetched"
            )
    })
    public ResponseEntity<List<ActorResponseDTO>> fetchActorList() {
        return ResponseEntity.ok(actorService.fetchActorList());
    }

    @GetMapping("/actor/{actorId}")
    @Operation(summary = "Fetch actor by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Actor successfully fetched"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            )
    })
    public ResponseEntity<ActorResponseDTO> fetchActorById(@PathVariable("actorId") Long actorId) {
        return ResponseEntity.ok(
                actorMapper.mapFromActorEntityToActorResponseDTO(actorService.fetchActorById(actorId)));
    }

    @PutMapping("/actor/{actorId}")
    @Operation(summary = "Update actor by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Actor successfully updated"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            )
    })
    public ResponseEntity<ActorResponseDTO> updateActorById(
            @PathVariable("actorId") Long actorId,
            @RequestBody @Valid ActorUpdateDTO actorUpdateDTO
    ) {
        return ResponseEntity.ok(actorService.updateActorById(actorId, actorUpdateDTO));
    }

    @DeleteMapping("/actor/{actorId}")
    @Operation(summary = "Delete actor by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            )
    })
    public ResponseEntity<Void> deleteActorById(@PathVariable("actorId") Long actorId) {
        actorService.deleteActorById(actorId);
        return ResponseEntity
                .noContent()
                .build();
    }
}
