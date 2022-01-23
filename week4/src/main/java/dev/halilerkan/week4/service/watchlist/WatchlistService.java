package dev.halilerkan.week4.service.watchlist;

import java.util.List;

public interface WatchlistService {

    Long create(Watchlist watchlist);

    List<Watchlist> fetchAll();

    Watchlist fetchById(Long id);

    Long update(Long id, Watchlist watchlist);

    void delete(Long id);

    List<Watchlist> fetchWatchlistsByMemberId(Long id);
}
