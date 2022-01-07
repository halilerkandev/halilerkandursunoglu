package dev.halilerkan.movie.dto.rating;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingRequestDTO {
    @Schema(example = "3")
    @NotNull
    @Min(value = 1 , message = "Point should be greater then then equal to 1")
    @Max(value = 5 , message = "Point should be less then then equal to 5")
    private Integer point;
}
