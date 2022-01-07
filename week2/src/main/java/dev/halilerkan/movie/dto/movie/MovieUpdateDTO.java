package dev.halilerkan.movie.dto.movie;

import dev.halilerkan.movie.entity.enumeration.Genre;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieUpdateDTO {
    @Schema(example = "Don't Look Up")
    @NotBlank
    private String name;

    @Schema(example = "COMEDY")
    private Genre genre;

    @Schema(example = "2021")
    @NotNull
    private Integer releaseYear;
}
