package dev.halilerkan.movie.service.watchlist;

import dev.halilerkan.movie.dto.watchlist.AddMovieToWatchlistRequestDTO;
import dev.halilerkan.movie.dto.watchlist.WatchlistCreateDTO;
import dev.halilerkan.movie.dto.watchlist.WatchlistResponseDTO;
import dev.halilerkan.movie.entity.MemberEntity;
import dev.halilerkan.movie.entity.MovieEntity;
import dev.halilerkan.movie.entity.WatchlistEntity;
import dev.halilerkan.movie.exception.NoContentException;
import dev.halilerkan.movie.mapper.WatchlistMapper;
import dev.halilerkan.movie.repository.WatchlistRepository;
import dev.halilerkan.movie.service.member.MemberService;
import dev.halilerkan.movie.service.movie.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class WatchlistServiceImpl implements WatchlistService {
    private final WatchlistRepository watchlistRepository;
    private final MemberService memberService;
    private final WatchlistMapper watchlistMapper;
    private final MovieService movieService;

    @Override
    public WatchlistResponseDTO createWatchlist(Long memberId, WatchlistCreateDTO watchlistCreateDTO) {
        MemberEntity memberEntity = memberService.fetchMemberById(memberId);
        WatchlistEntity watchlistEntity = watchlistRepository.save(
                WatchlistEntity
                        .builder()
                        .member(memberEntity)
                        .name(watchlistCreateDTO.getName())
                        .build()
        );
        return watchlistMapper.mapFromWatchlistEntityToWatchlistResponseDTO(watchlistEntity);
    }

    @Override
    public void addMovieToWatchlist(
            Long memberId,
            Long watchlistId,
            AddMovieToWatchlistRequestDTO addMovieToWatchlistRequestDTO
    ) {
        MemberEntity memberEntity = memberService.fetchMemberById(memberId);
        WatchlistEntity watchlistEntity = fetchWatchlistById(watchlistId);

        if(watchlistEntity.getMember().equals(memberEntity)) {
            MovieEntity movieEntity = movieService.fetchMovieById(addMovieToWatchlistRequestDTO.getMovieId());
            watchlistEntity.addMovie(movieEntity);
        }
    }

    public WatchlistEntity fetchWatchlistById(Long watchlistId) throws NoContentException {
        return  watchlistRepository
                .findById(watchlistId)
                .orElseThrow(NoContentException.buildWithSupplier());
    }
}
