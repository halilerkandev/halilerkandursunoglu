package dev.halilerkan.week3.dao.moviewatchlist;

import dev.halilerkan.week3.dao.movie.MovieEntity;
import dev.halilerkan.week3.dao.watchlist.WatchlistEntity;

public interface MovieWatchlistDao {
    MovieWatchlistEntity addMovieToWatchlist(WatchlistEntity watchlistEntity, MovieEntity movieEntity);
}
