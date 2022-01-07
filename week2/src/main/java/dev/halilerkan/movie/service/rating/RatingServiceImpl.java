package dev.halilerkan.movie.service.rating;

import dev.halilerkan.movie.dto.rating.RatingRequestDTO;
import dev.halilerkan.movie.entity.MemberEntity;
import dev.halilerkan.movie.entity.MovieEntity;
import dev.halilerkan.movie.entity.RatingEntity;
import dev.halilerkan.movie.exception.NoContentException;
import dev.halilerkan.movie.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;

    @Override
    public RatingEntity createRating(
            MemberEntity memberEntity,
            MovieEntity movieEntity,
            RatingRequestDTO ratingRequestDTO
    ) throws NoContentException {
        return ratingRepository.save(RatingEntity
                .builder()
                .member(memberEntity)
                .movie(movieEntity)
                .point(ratingRequestDTO.getPoint())
                .build());
    }

    @Override
    public RatingEntity updateRating(RatingEntity ratingEntity, Integer point) {
        ratingEntity.setPoint(point);
        return ratingRepository.save(ratingEntity);
    }

    @Override
    public Optional<RatingEntity> findByMemberIdAndMovieId(Long memberId, Long movieId) {
        return ratingRepository.findByMemberIdAndMovieId(memberId, movieId);
    }
}
