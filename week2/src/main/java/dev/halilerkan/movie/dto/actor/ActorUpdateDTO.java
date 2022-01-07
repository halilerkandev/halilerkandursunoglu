package dev.halilerkan.movie.dto.actor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorUpdateDTO {
    @Schema(example = "Leonardo")
    @NotBlank
    private String firstName;

    @Schema(example = "DiCaprio")
    @NotBlank
    private String lastName;
}
