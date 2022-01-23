package dev.halilerkan.week4.controller.rate.response;

import dev.halilerkan.week4.service.rate.Rate;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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