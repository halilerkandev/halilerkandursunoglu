package dev.halilerkan.week4.dao.moviewatchlist;

import dev.halilerkan.week4.dao.movie.MovieEntity;
import dev.halilerkan.week4.dao.watchlist.WatchlistEntity;

public interface MovieWatchlistDao {
    MovieWatchlistEntity addMovieToWatchlist(WatchlistEntity watchlistEntity, MovieEntity movieEntity);
}
