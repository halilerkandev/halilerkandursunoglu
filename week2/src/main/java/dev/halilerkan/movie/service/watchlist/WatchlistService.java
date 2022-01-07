package dev.halilerkan.movie.service.watchlist;

import dev.halilerkan.movie.dto.watchlist.AddMovieToWatchlistRequestDTO;
import dev.halilerkan.movie.dto.watchlist.WatchlistCreateDTO;
import dev.halilerkan.movie.dto.watchlist.WatchlistResponseDTO;
import dev.halilerkan.movie.entity.WatchlistEntity;
import dev.halilerkan.movie.exception.NoContentException;

public interface WatchlistService {
    WatchlistResponseDTO createWatchlist(Long memberId, WatchlistCreateDTO watchlistCreateDTO);

    void addMovieToWatchlist(
            Long memberId,
            Long watchlistId,
            AddMovieToWatchlistRequestDTO addMovieToWatchlistRequestDTO
    );

    WatchlistEntity fetchWatchlistById(Long watchlistId) throws NoContentException;
}
