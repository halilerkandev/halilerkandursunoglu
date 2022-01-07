package dev.halilerkan.movie.dto.director;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectorCreateDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long directorId;

    @Schema(example = "Adam")
    @NotBlank
    private String firstName;

    @Schema(example = "McKay")
    @NotBlank
    private String lastName;
}
