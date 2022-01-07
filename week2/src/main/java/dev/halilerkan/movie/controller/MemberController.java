package dev.halilerkan.movie.controller;

import dev.halilerkan.movie.dto.member.MemberCreateDTO;
import dev.halilerkan.movie.dto.member.MemberResponseDTO;
import dev.halilerkan.movie.dto.member.MemberUpdateDTO;
import dev.halilerkan.movie.dto.movie.MovieResponseDTO;
import dev.halilerkan.movie.dto.rating.RatingRequestDTO;
import dev.halilerkan.movie.dto.rating.RatingResponseDTO;
import dev.halilerkan.movie.dto.watchlist.AddMovieToWatchlistRequestDTO;
import dev.halilerkan.movie.dto.watchlist.WatchlistCreateDTO;
import dev.halilerkan.movie.dto.watchlist.WatchlistResponseDTO;
import dev.halilerkan.movie.entity.MemberEntity;
import dev.halilerkan.movie.entity.WatchlistEntity;
import dev.halilerkan.movie.mapper.MemberMapper;
import dev.halilerkan.movie.mapper.MovieMapper;
import dev.halilerkan.movie.mapper.WatchlistMapper;
import dev.halilerkan.movie.service.member.MemberService;
import dev.halilerkan.movie.service.watchlist.WatchlistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Member")
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final WatchlistService watchlistService;
    private final WatchlistMapper watchlistMapper;
    private final MovieMapper movieMapper;

    @PostMapping("/member")
    @Operation(summary = "Create member")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Member successfully created"
            )
    })
    public ResponseEntity<MemberResponseDTO> createMember(
            @RequestBody @Valid MemberCreateDTO memberCreateDTO
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberService.createMember(memberCreateDTO));
    }

    @GetMapping("/members")
    @Operation(summary = "Fetch member list")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Member list successfully fetched"
            )
    })
    public ResponseEntity<List<MemberResponseDTO>> fetchMemberList() {
        return ResponseEntity.ok(memberService.fetchMemberList());
    }

    @GetMapping("/member/{memberId}")
    @Operation(summary = "Fetch member by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Member successfully fetched"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            )
    })
    public ResponseEntity<MemberResponseDTO> fetchMemberById(@PathVariable("memberId") Long memberId) {
        MemberEntity memberEntity = memberService.fetchMemberById(memberId);
        return ResponseEntity.ok(memberMapper.mapFromMemberEntityToMemberResponseDTO(memberEntity));
    }

    @PutMapping("/member/{memberId}")
    @Operation(summary = "Update member by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Member successfully updated"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            )
    })
    public ResponseEntity<MemberResponseDTO> updateMemberById(
            @PathVariable("memberId") Long memberId,
            @RequestBody @Valid MemberUpdateDTO memberUpdateDTO
    ) {
        return ResponseEntity.ok(memberService.updateMemberById(memberId, memberUpdateDTO));
    }

    @DeleteMapping("/member/{memberId}")
    @Operation(summary = "Delete member by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            )
    })
    public ResponseEntity<Void> deleteMemberById(@PathVariable("memberId") Long memberId) {
        memberService.deleteMemberById(memberId);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/member/{memberId}/movie/{movieId}/rating")
    @Operation(summary = "Rating a movie by a member")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Movie successfully rated by member"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            )
    })
    public ResponseEntity<RatingResponseDTO> rateMovie(
            @PathVariable("memberId") Long memberId,
            @PathVariable("movieId") Long movieId,
            @RequestBody @Valid RatingRequestDTO ratingRequestDTO
    ) {
        return ResponseEntity.ok(memberService.rateMovie(memberId, movieId, ratingRequestDTO));
    }

    @PostMapping("/member/{memberId}/watchlist")
    @Operation(summary = "Create a watchlist by a member")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Watchlist successfully created by member"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            )
    })
    public ResponseEntity<WatchlistResponseDTO> createWatchlist(
            @PathVariable("memberId") Long memberId,
            @RequestBody @Valid WatchlistCreateDTO watchlistCreateDTO
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(watchlistService.createWatchlist(memberId, watchlistCreateDTO));
    }

    @PostMapping("/member/{memberId}/watchlist/{watchlistId}/movie")
    @Operation(summary = "Add a movie to watchlist by a member")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Movie successfully added to watchlist by member"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            )
    })
    public ResponseEntity<Void> addMovieToWatchlist(
            @PathVariable("memberId") Long memberId,
            @PathVariable("watchlistId") Long watchlistId,
            @RequestBody @Valid AddMovieToWatchlistRequestDTO addMovieToWatchlistRequestDTO
    ) {
        watchlistService.addMovieToWatchlist(memberId, watchlistId, addMovieToWatchlistRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/member/{memberId}/watchlists")
    @Operation(summary = "Get member's watchlists")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Watchlists successfully fetched by member"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            )
    })
    public ResponseEntity<List<WatchlistResponseDTO>> fetchWatchlistsByMemberId(
            @PathVariable("memberId") Long memberId
    ) {
        MemberEntity memberEntity = memberService.fetchMemberById(memberId);
        List<WatchlistResponseDTO> watchlistResponseDTOS = memberEntity.getWatchlists().stream().map(
                watchlistMapper::mapFromWatchlistEntityToWatchlistResponseDTO
        ).collect(Collectors.toList());
        return ResponseEntity.ok(watchlistResponseDTOS);
    }

    @GetMapping("/member/{memberId}/watchlist/{watchlistId}/movies")
    @Operation(summary = "Get member's watchlist's movies")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Watchlists successfully fetched by member"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            )
    })
    public ResponseEntity<List<MovieResponseDTO>> fetchWatchlistMovies(
            @PathVariable("memberId") Long memberId,
            @PathVariable("watchlistId") Long watchlistId
    ) {
        WatchlistEntity watchlistEntity = watchlistService.fetchWatchlistById(watchlistId);
        if(watchlistEntity.getMember().getMemberId().equals(memberId)) {
            List<MovieResponseDTO> movieResponseDTOS = watchlistEntity.getMovies().stream().map(
                    movieMapper::mapFromMovieEntityToMovieResponseDTO
            ).collect(Collectors.toList());
            return ResponseEntity.ok(movieResponseDTOS);
        }
        return ResponseEntity.noContent().build();
    }
}
