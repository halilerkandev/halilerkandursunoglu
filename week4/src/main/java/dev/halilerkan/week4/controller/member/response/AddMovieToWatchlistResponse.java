package dev.halilerkan.week4.controller.member.response;

import dev.halilerkan.week4.service.moviewatchlist.MovieWatchlist;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddMovieToWatchlistResponse {

    @Schema(example = "1")
    private Long watchlistId;

    @Schema(example = "1")
    private Long movieId;

    public static AddMovieToWatchlistResponse valueOf(MovieWatchlist movieWatchlist) {
        return AddMovieToWatchlistResponse.builder()
                .watchlistId(movieWatchlist.getWatchlistId())
                .movieId(movieWatchlist.getMovieId())
                .build();
    }

}
