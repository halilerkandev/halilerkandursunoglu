package dev.halilerkan.movie.dto.actor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorResponseDTO {
    @Schema(example = "1")
    @NotNull
    private Long actorId;

    @Schema(example = "Leonardo")
    @NotBlank
    private String firstName;

    @Schema(example = "DiCaprio")
    @NotBlank
    private String lastName;
}
