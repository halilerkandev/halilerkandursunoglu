package dev.halilerkan.movie.dto.watchlist;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchlistCreateDTO {
    @Schema(example = "Comedy Films")
    @NotBlank
    private String name;
}
