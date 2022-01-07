package dev.halilerkan.movie.dto.movie;

import dev.halilerkan.movie.entity.ActorEntity;
import dev.halilerkan.movie.entity.DirectorEntity;
import dev.halilerkan.movie.entity.enumeration.Genre;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDTO {
    @Schema(example = "1")
    @NotNull
    private Long movieId;

    @Schema(example = "Don't Look Up")
    @NotBlank
    private String name;

    @Schema(example = "COMEDY")
    private Genre genre;

    @Schema(example = "2021")
    @NotNull
    private Integer releaseYear;

    @Schema
    private DirectorEntity director;

    @Schema
    private List<ActorEntity> cast;
}
