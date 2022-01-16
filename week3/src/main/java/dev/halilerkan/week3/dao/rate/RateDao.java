package dev.halilerkan.week3.dao.rate;

import java.util.List;

public interface RateDao {

    void rateMovie(RateEntity entity);

    List<RateEntity> fetchByMovieId(Long movieId);

    List<RateEntity> fetchByMemberId(Long memberId);

}
