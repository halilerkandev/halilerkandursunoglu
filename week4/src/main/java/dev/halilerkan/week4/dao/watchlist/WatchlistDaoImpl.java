package dev.halilerkan.week4.dao.watchlist;

import dev.halilerkan.week4.common.exception.NoContentException;
import dev.halilerkan.week4.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WatchlistDaoImpl implements WatchlistDao {

    private final WatchlistJpaRepository watchlistJpaRepository;

    @Override
    public Long create(WatchlistEntity entity) {
        return watchlistJpaRepository.save(entity).getId();
    }

    @Override
    public List<WatchlistEntity> fetchAll() {
        return watchlistJpaRepository.findAll();
    }

    @Override
    public WatchlistEntity fetchById(Long id) {
        return watchlistJpaRepository
                .findById(id)
                .orElseThrow(NotFoundException.withSupplier());
    }

    @Override
    public Long update(Long id, WatchlistEntity entity) {
        WatchlistEntity watchlistEntity = watchlistJpaRepository.findById(id).orElseThrow(NotFoundException.withSupplier());
        watchlistEntity.setUpdatedDate(Instant.now());
        watchlistEntity.setName(entity.getName());
        watchlistJpaRepository.save(watchlistEntity);
        return watchlistEntity.getId();
    }

    @Override
    public void delete(Long id) {
        WatchlistEntity watchlistEntity = watchlistJpaRepository.findById(id).orElseThrow(NoContentException.withSupplier());
        watchlistJpaRepository.deleteById(watchlistEntity.getId());
    }

    @Override
    public List<WatchlistEntity> fetchWatchlistsByMemberId(Long id) {
        return watchlistJpaRepository.findAllByMember_Id(id);
    }

}
