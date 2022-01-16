package dev.halilerkan.week3.service.movie;

import dev.halilerkan.week3.common.enumeration.MovieGenre;
import dev.halilerkan.week3.dao.movie.MovieEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
@EqualsAndHashCode
public class Movie implements Serializable {

    private String name;
    private MovieGenre genre;
    private Integer releaseYear;

    MovieEntity toMovieEntity() {
        return MovieEntity.builder()
                .name(getName())
                .genre(getGenre())
                .releaseYear(getReleaseYear())
                .build();
    }

    public static Movie valueOf(MovieEntity movieEntity) {
        return Movie.builder()
                .name(movieEntity.getName())
                .genre(movieEntity.getGenre())
                .releaseYear(movieEntity.getReleaseYear())
                .build();
    }

}
