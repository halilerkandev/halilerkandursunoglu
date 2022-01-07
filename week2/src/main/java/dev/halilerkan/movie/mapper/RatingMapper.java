package dev.halilerkan.movie.mapper;

import dev.halilerkan.movie.dto.rating.RatingResponseDTO;
import dev.halilerkan.movie.entity.RatingEntity;
import org.mapstruct.Mapper;

@Mapper
public interface RatingMapper {
    RatingResponseDTO mapFromRatingEntityToRatingResponseDTO(RatingEntity ratingEntity);
}
