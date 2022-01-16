package dev.halilerkan.week3.dao.moviewatchlist;

import dev.halilerkan.week3.dao.movie.MovieEntity;
import dev.halilerkan.week3.dao.watchlist.WatchlistEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieWatchlistDaoImpl implements MovieWatchlistDao {
    private final MovieWatchlistJpaRepository movieWatchlistJpaRepository;

    @Override
    public MovieWatchlistEntity addMovieToWatchlist(
            WatchlistEntity watchlistEntity,
            MovieEntity movieEntity
    ) {
        MovieWatchlistEntity movieWatchlistEntity = MovieWatchlistEntity.builder()
                .movie(movieEntity)
                .watchlist(watchlistEntity).build();
        Optional<MovieWatchlistEntity> optionalMovieWatchlistEntity = movieWatchlistJpaRepository
                .findByMovie_IdAndWatchlist_Id(movieEntity.getId(), watchlistEntity.getId());
        if(optionalMovieWatchlistEntity.isPresent()) {
            return optionalMovieWatchlistEntity.get();
        }
        movieWatchlistJpaRepository.save(movieWatchlistEntity);
        return movieWatchlistEntity;
    }
}
