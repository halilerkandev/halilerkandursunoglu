package dev.halilerkan.week3.controller.movie.request;

import dev.halilerkan.week3.common.enumeration.MovieGenre;
import dev.halilerkan.week3.service.movie.Movie;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MovieUpdateRequest {

    @Schema(example = "Don't Look Up")
    @NotBlank
    private String name;

    @Schema(example = "COMEDY")
    @NotNull
    private MovieGenre genre;

    @Schema(example = "2021")
    @NotNull
    private Integer releaseYear;

    public Movie toMovie() {
        return Movie.builder()
                .name(name)
                .genre(genre)
                .releaseYear(releaseYear)
                .build();
    }

}
