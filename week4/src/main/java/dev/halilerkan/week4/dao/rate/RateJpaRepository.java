package dev.halilerkan.week4.dao.rate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RateJpaRepository extends JpaRepository<RateEntity, Long> {

    List<RateEntity> findAllByMovie_Id(Long movieId);

    List<RateEntity> findAllByMember_Id(Long memberId);

    @Query("select r from rate r where r.member.id = ?1 and r.movie.id = ?2")
    Optional<RateEntity> findByMemberIdAndMovieId(Long memberId, Long movieId);

}
