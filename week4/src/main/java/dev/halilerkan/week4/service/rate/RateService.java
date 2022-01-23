package dev.halilerkan.week4.service.rate;

import java.util.List;

public interface RateService {

    void rateMovie(Rate rate);

    List<Rate> fetchByMovieId(Long movieId);

    List<Rate> fetchByMemberId(Long memberId);

}
