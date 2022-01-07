package dev.halilerkan.movie.dto.watchlist;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddMovieToWatchlistRequestDTO {
    @Schema(example = "1")
    @NotNull
    private Long movieId;
}
