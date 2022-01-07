package dev.halilerkan.movie.service.rating;

import dev.halilerkan.movie.dto.rating.RatingRequestDTO;
import dev.halilerkan.movie.entity.MemberEntity;
import dev.halilerkan.movie.entity.MovieEntity;
import dev.halilerkan.movie.entity.RatingEntity;
import dev.halilerkan.movie.exception.NoContentException;

import java.util.Optional;

public interface RatingService {
    RatingEntity createRating(MemberEntity memberEntity, MovieEntity movieEntity, RatingRequestDTO ratingRequestDTO) throws NoContentException;

    RatingEntity updateRating(RatingEntity ratingEntity, Integer point);

    Optional<RatingEntity> findByMemberIdAndMovieId(Long memberId, Long movieId);
}
