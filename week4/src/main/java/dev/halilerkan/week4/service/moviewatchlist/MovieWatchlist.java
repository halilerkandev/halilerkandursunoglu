package dev.halilerkan.week4.service.moviewatchlist;

import dev.halilerkan.week4.dao.movie.MovieEntity;
import dev.halilerkan.week4.dao.moviewatchlist.MovieWatchlistEntity;
import dev.halilerkan.week4.dao.watchlist.WatchlistEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class MovieWatchlist {

    private Long watchlistId;
    private Long movieId;

    public MovieWatchlistEntity toMovieWatchlistEntity(
            MovieEntity movieEntity,
            WatchlistEntity watchlistEntity
    ) {
        MovieWatchlistEntity entity = new MovieWatchlistEntity();
        entity.setMovie(movieEntity);
        entity.setWatchlist(watchlistEntity);
        return entity;
    }

    public static MovieWatchlist valueOf(MovieWatchlistEntity entity) {
        return MovieWatchlist.builder()
                .movieId(entity.getMovie().getId())
                .watchlistId(entity.getWatchlist().getId())
                .build();
    }
}
