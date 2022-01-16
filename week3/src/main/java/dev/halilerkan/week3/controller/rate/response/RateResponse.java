package dev.halilerkan.week3.controller.rate.response;

import dev.halilerkan.week3.service.rate.Rate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RateResponse {

    private Long memberId;
    private Long movieId;
    private Integer point;

    public static RateResponse valueOf(Rate rate) {
        return RateResponse.builder()
                .memberId(rate.getMemberId())
                .movieId(rate.getMovieId())
                .point(rate.getPoint())
                .build();
    }
}
