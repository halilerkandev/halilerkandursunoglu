package dev.halilerkan.week4.dao.moviewatchlist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieWatchlistJpaRepository extends JpaRepository<MovieWatchlistEntity, Long> {
    Optional<MovieWatchlistEntity> findByMovie_IdAndWatchlist_Id(Long movieId, Long watchlistId);
}
