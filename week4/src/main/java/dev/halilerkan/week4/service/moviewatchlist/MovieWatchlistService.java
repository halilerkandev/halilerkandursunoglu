package dev.halilerkan.week4.service.moviewatchlist;

public interface MovieWatchlistService {

    MovieWatchlist addMovieToWatchlist(Long memberId, Long watchlistId, Long movieId);

}
