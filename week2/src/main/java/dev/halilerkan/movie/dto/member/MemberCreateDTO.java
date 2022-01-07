package dev.halilerkan.movie.dto.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberCreateDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long memberId;

    @Schema(example = "Halil Erkan")
    @NotBlank
    private String firstName;

    @Schema(example = "Dursunoğlu")
    @NotBlank
    private String lastName;
}