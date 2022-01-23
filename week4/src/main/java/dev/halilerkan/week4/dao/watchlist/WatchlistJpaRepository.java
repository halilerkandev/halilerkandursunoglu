package dev.halilerkan.week4.dao.watchlist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchlistJpaRepository extends JpaRepository<WatchlistEntity, Long> {
    List<WatchlistEntity> findAllByMember_Id(Long id);
}