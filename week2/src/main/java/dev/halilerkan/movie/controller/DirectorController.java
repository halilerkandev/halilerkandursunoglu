package dev.halilerkan.movie.controller;

import dev.halilerkan.movie.dto.director.DirectorCreateDTO;
import dev.halilerkan.movie.dto.director.DirectorResponseDTO;
import dev.halilerkan.movie.dto.director.DirectorUpdateDTO;
import dev.halilerkan.movie.mapper.DirectorMapper;
import dev.halilerkan.movie.service.director.DirectorService;
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
@Tag(name = "Directors")
public class DirectorController {
    private final DirectorService directorService;
    private final DirectorMapper directorMapper;

    @PostMapping("/director")
    @Operation(summary = "Create director")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Director successfully created"
            )
    })
    public ResponseEntity<DirectorResponseDTO> createDirector(
            @RequestBody @Valid DirectorCreateDTO directorCreateDTO
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(directorService.createDirector(directorCreateDTO));
    }

    @GetMapping("/directors")
    @Operation(summary = "Fetch director list")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Director list successfully fetched"
            )
    })
    public ResponseEntity<List<DirectorResponseDTO>> fetchDirectorList() {
        return ResponseEntity.ok(directorService.fetchDirectorList());
    }

    @GetMapping("/director/{directorId}")
    @Operation(summary = "Fetch director by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Director successfully fetched"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            )
    })
    public ResponseEntity<DirectorResponseDTO> fetchDirectorById(@PathVariable("directorId") Long directorId) {
        return ResponseEntity.ok(
                directorMapper.mapFromDirectorEntityToDirectorResponseDTO(
                        directorService.fetchDirectorById(directorId))
        );
    }

    @PutMapping("/director/{directorId}")
    @Operation(summary = "Update director by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Director successfully updated"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            )
    })
    public ResponseEntity<DirectorResponseDTO> updateDirectorById(
            @PathVariable("directorId") Long directorId,
            @RequestBody @Valid DirectorUpdateDTO directorUpdateDTO
    ) {
        return ResponseEntity.ok(directorService.updateDirectorById(directorId, directorUpdateDTO));
    }

    @DeleteMapping("/director/{directorId}")
    @Operation(summary = "Delete director by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            )
    })
    public ResponseEntity<Void> deleteDirectorById(@PathVariable("directorId") Long directorId) {
        directorService.deleteDirectorById(directorId);
        return ResponseEntity
                .noContent()
                .build();
    }
}
