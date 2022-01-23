package dev.halilerkan.week4.service.watchlist;

import dev.halilerkan.week4.dao.member.MemberDao;
import dev.halilerkan.week4.dao.member.MemberEntity;
import dev.halilerkan.week4.dao.watchlist.WatchlistDao;
import dev.halilerkan.week4.dao.watchlist.WatchlistEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WatchlistServiceImpl implements WatchlistService {

    private final WatchlistDao watchlistDao;
    private final MemberDao memberDao;
    //private final RedisTemplate<Long, Movie> movieRedisTemplate;

    @Override
    public Long create(Watchlist watchlist) {
        MemberEntity memberEntity = memberDao.fetchById(watchlist.getMemberId());
        WatchlistEntity entity = watchlist.toWatchlistEntity(memberEntity);
        return watchlistDao.create(entity);
    }

    @Override
    public List<Watchlist> fetchAll() {
        List<WatchlistEntity> entityList = watchlistDao.fetchAll();
        return entityList
                .stream()
                .map(Watchlist::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public Watchlist fetchById(Long id) {
        WatchlistEntity entity = watchlistDao.fetchById(id);
        return Watchlist.valueOf(entity);
    }

    @Override
    public Long update(Long id, Watchlist watchlist) {
        MemberEntity memberEntity = memberDao.fetchById(watchlist.getMemberId());
        WatchlistEntity entity = watchlist.toWatchlistEntity(memberEntity);
        return watchlistDao.update(id, entity);
    }

    @Override
    public void delete(Long id) {
        watchlistDao.delete(id);
    }

    @Override
    public List<Watchlist> fetchWatchlistsByMemberId(Long id) {
        MemberEntity memberEntity = memberDao.fetchById(id);
        List<WatchlistEntity> entityList = watchlistDao.fetchWatchlistsByMemberId(memberEntity.getId());
        return entityList
                .stream()
                .map(Watchlist::valueOf)
                .collect(Collectors.toList());
    }

}
