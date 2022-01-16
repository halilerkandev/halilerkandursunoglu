package dev.halilerkan.week3.service.rate;

import dev.halilerkan.week3.dao.member.MemberDao;
import dev.halilerkan.week3.dao.member.MemberEntity;
import dev.halilerkan.week3.dao.movie.MovieDao;
import dev.halilerkan.week3.dao.movie.MovieEntity;
import dev.halilerkan.week3.dao.rate.RateDao;
import dev.halilerkan.week3.dao.rate.RateEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {

    private final RateDao rateDao;
    private final MovieDao movieDao;
    private final MemberDao memberDao;

    @Override
    public void rateMovie(Rate rate) {
        MovieEntity movieEntity = movieDao.fetchById(rate.getMovieId());
        MemberEntity memberEntity = memberDao.fetchById(rate.getMemberId());
        RateEntity entity = rate.toRateEntity(movieEntity, memberEntity);
        rateDao.rateMovie(entity);
    }

    @Override
    public List<Rate> fetchByMovieId(Long movieId) {
        return rateDao.fetchByMovieId(movieId)
                .stream()
                .map(Rate::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<Rate> fetchByMemberId(Long memberId) {
        return rateDao.fetchByMemberId(memberId)
                .stream()
                .map(Rate::valueOf)
                .collect(Collectors.toList());
    }
}
