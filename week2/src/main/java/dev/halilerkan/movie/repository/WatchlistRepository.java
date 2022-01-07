package dev.halilerkan.movie.repository;

import dev.halilerkan.movie.entity.WatchlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchlistRepository extends JpaRepository<WatchlistEntity, Long> {

}
