package dev.halilerkan.movie.dto.director;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddDirectorToMovieRequestDTO {
    @Schema(example = "1")
    @NotNull
    private Long directorId;
}
