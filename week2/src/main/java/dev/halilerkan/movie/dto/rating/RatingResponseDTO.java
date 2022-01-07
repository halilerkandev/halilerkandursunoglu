package dev.halilerkan.movie.dto.rating;

import dev.halilerkan.movie.entity.MemberEntity;
import dev.halilerkan.movie.entity.MovieEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingResponseDTO {
    @Schema(example = "1")
    @NotNull
    private Long ratingId;

    @Schema
    @NotBlank
    private MemberEntity member;

    @Schema(example = "1")
    @NotBlank
    private MovieEntity movie;

    @Schema
    @NotNull
    private Integer point;
}