package dev.halilerkan.week4.service.moviewatchlist;

import dev.halilerkan.week4.dao.member.MemberDao;
import dev.halilerkan.week4.dao.movie.MovieDao;
import dev.halilerkan.week4.dao.movie.MovieEntity;
import dev.halilerkan.week4.dao.moviewatchlist.MovieWatchlistDao;
import dev.halilerkan.week4.dao.moviewatchlist.MovieWatchlistEntity;
import dev.halilerkan.week4.dao.watchlist.WatchlistDao;
import dev.halilerkan.week4.dao.watchlist.WatchlistEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieWatchlistServiceImpl implements MovieWatchlistService {

    private final MemberDao memberDao;
    private final WatchlistDao watchlistDao;
    private final MovieDao movieDao;
    private final MovieWatchlistDao movieWatchlistDao;

    @Override
    public MovieWatchlist addMovieToWatchlist(Long memberId, Long watchlistId, Long movieId) {
        memberDao.fetchById(memberId);
        WatchlistEntity watchlistEntity = watchlistDao.fetchById(watchlistId);
        MovieEntity movieEntity = movieDao.fetchById(movieId);
        MovieWatchlistEntity movieWatchlistEntity = movieWatchlistDao.addMovieToWatchlist(watchlistEntity, movieEntity);
        return MovieWatchlist.valueOf(movieWatchlistEntity);
    }

}
