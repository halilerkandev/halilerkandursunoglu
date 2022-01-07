package dev.halilerkan.movie.repository;

import dev.halilerkan.movie.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, Long> {
    @Query("select r from RatingEntity r where r.member.memberId = ?1 and r.movie.movieId = ?2")
    Optional<RatingEntity> findByMemberIdAndMovieId(Long memberId, Long movieId);
}
