package dev.halilerkan.week3.service.moviewatchlist;

public interface MovieWatchlistService {

    MovieWatchlist addMovieToWatchlist(Long memberId, Long watchlistId, Long movieId);

}
