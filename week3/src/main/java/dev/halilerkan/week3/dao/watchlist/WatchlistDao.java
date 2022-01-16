package dev.halilerkan.week3.dao.watchlist;

import java.util.List;

public interface WatchlistDao {

    Long create(WatchlistEntity entity);

    List<WatchlistEntity> fetchAll();

    WatchlistEntity fetchById(Long id);

    Long update(Long id, WatchlistEntity entity);

    void delete(Long id);

    List<WatchlistEntity> fetchWatchlistsByMemberId(Long id);
}
