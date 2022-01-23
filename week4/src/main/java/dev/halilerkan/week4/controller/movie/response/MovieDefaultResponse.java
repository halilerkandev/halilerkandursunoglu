package dev.halilerkan.week4.controller.movie.response;

import dev.halilerkan.week4.common.enumeration.MovieGenre;
import dev.halilerkan.week4.service.movie.Movie;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MovieDefaultResponse {

    @Schema(example = "Don't Look Up")
    private String name;

    @Schema(example = "COMEDY")
    private MovieGenre genre;

    @Schema(example = "2021")
    private Integer releaseYear;

    public static MovieDefaultResponse valueOf(Movie movie) {
        return MovieDefaultResponse.builder()
                .name(movie.getName())
                .genre(movie.getGenre())
                .releaseYear(movie.getReleaseYear())
                .build();
    }

}
