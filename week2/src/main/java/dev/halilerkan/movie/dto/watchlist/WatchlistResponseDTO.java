package dev.halilerkan.movie.dto.watchlist;

import dev.halilerkan.movie.entity.MemberEntity;
import dev.halilerkan.movie.entity.MovieEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WatchlistResponseDTO {
    @Schema(example = "1")
    @NotNull
    private Long watchlistId;

    @Schema(example = "Comedy Films")
    @NotBlank
    private String name;

    @Schema
    @NotBlank
    private MemberEntity member;

    @Schema
    @NotBlank
    private List<MovieEntity> movies;
}