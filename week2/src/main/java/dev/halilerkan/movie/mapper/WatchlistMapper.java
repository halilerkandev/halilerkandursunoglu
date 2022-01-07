package dev.halilerkan.movie.mapper;

import dev.halilerkan.movie.dto.watchlist.WatchlistResponseDTO;
import dev.halilerkan.movie.entity.WatchlistEntity;
import org.mapstruct.Mapper;

@Mapper
public interface WatchlistMapper {
    WatchlistResponseDTO mapFromWatchlistEntityToWatchlistResponseDTO(WatchlistEntity watchlistEntity);
}
