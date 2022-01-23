package dev.halilerkan.week4.controller.member;

import dev.halilerkan.week4.controller.member.request.*;
import dev.halilerkan.week4.controller.member.response.*;
import dev.halilerkan.week4.service.member.Member;
import dev.halilerkan.week4.service.member.MemberService;
import dev.halilerkan.week4.service.moviewatchlist.MovieWatchlist;
import dev.halilerkan.week4.service.moviewatchlist.MovieWatchlistService;
import dev.halilerkan.week4.service.watchlist.Watchlist;
import dev.halilerkan.week4.service.watchlist.WatchlistService;
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
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Tag(name = "Members")
public class MemberController {

    private final MemberService memberService;
    private final WatchlistService watchlistService;
    private final MovieWatchlistService movieWatchlistService;

    @PostMapping
    @Operation(summary = "Create member")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Member created"
            )
    })
    public ResponseEntity<MemberCreateResponse> create(
            @RequestBody @Valid MemberCreateRequest memberCreateRequest
    ) {
        Member member = memberCreateRequest.toMember();
        Long memberId = memberService.create(member);
        MemberCreateResponse memberCreateResponse = MemberCreateResponse.valueOf(memberId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberCreateResponse);
    }

    @GetMapping
    @Operation(summary = "Fetch member list")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Member list fetched"
            )
    })
    public ResponseEntity<List<MemberDefaultResponse>> fetchAll() {
        List<Member> memberList = memberService.fetchAll();
        return ResponseEntity
                .ok()
                .body(memberList
                        .stream()
                        .map(MemberDefaultResponse::valueOf)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{memberId}")
    @Operation(summary = "Fetch member by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Member fetched"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content()
            )
    })
    public ResponseEntity<MemberDefaultResponse> fetchById(
            @PathVariable Long memberId
    ) {
        Member member = memberService.fetchById(memberId);
        return ResponseEntity.ok(MemberDefaultResponse.valueOf(member));
    }

    @PutMapping("/{memberId}")
    @Operation(summary = "Update member by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Member updated"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content()
            )
    })
    public ResponseEntity<MemberUpdateResponse> update(
            @PathVariable Long memberId,
            @RequestBody @Valid MemberUpdateRequest memberUpdateRequest
    ) {
        Member member = memberUpdateRequest.toMember();
        Long id = memberService.update(memberId, member);
        return ResponseEntity.ok(MemberUpdateResponse.valueOf(id));
    }

    @DeleteMapping("/{memberId}")
    @Operation(summary = "Delete member by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            )
    })
    public ResponseEntity<Void> deleteById(
            @PathVariable Long memberId
    ) {
        memberService.delete(memberId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{memberId}/watchlists")
    @Operation(summary = "Create watchlist")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Watchlist created"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            )
    })
    public ResponseEntity<WatchlistCreateResponse> createWatchlist(
            @PathVariable Long memberId,
            @RequestBody @Valid WatchlistCreateRequest watchlistCreateRequest
    ) {
        Watchlist watchlist = watchlistCreateRequest.toWatchlist(memberId);
        Long watchlistId = watchlistService.create(watchlist);
        WatchlistCreateResponse watchlistCreateResponse = WatchlistCreateResponse.valueOf(watchlistId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(watchlistCreateResponse);
    }

    @GetMapping("/{memberId}/watchlists")
    @Operation(summary = "Fetch watchlists")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Watchlists fetched"
            )
    })
    public ResponseEntity<List<WatchlistDefaultResponse>> fetchWatchlistsByMemberId(
            @PathVariable Long memberId
    ) {
        List<Watchlist> watchlists = watchlistService.fetchWatchlistsByMemberId(memberId);
        return ResponseEntity
                .ok()
                .body(watchlists
                        .stream()
                        .map(WatchlistDefaultResponse::valueOf)
                        .collect(Collectors.toList()));
    }

    @PutMapping("/{memberId}/watchlists/{watchlistId}")
    @Operation(summary = "Update watchlist")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Watchlist updated"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content()
            )
    })
    public ResponseEntity<WatchlistUpdateResponse> updateWatchlist(
            @PathVariable Long memberId,
            @PathVariable Long watchlistId,
            @RequestBody @Valid WatchlistUpdateRequest watchlistUpdateRequest
    ) {
        Watchlist watchlist = watchlistUpdateRequest.toWatchlist(memberId);
        Long updatedWatchlistId = watchlistService.update(watchlistId, watchlist);
        return ResponseEntity.ok(WatchlistUpdateResponse.valueOf(updatedWatchlistId));
    }

    @GetMapping("/{memberId}/watchlists/{watchlistId}")
    @Operation(summary = "Fetch watchlist")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Watchlist fetched"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content()
            )
    })
    public ResponseEntity<WatchlistDefaultResponse> fetchWatchlist(
            @PathVariable Long memberId,
            @PathVariable Long watchlistId
    ) {
        memberService.fetchById(memberId);
        Watchlist watchlist = watchlistService.fetchById(watchlistId);
        return ResponseEntity.ok(WatchlistDefaultResponse.valueOf(watchlist));
    }

    @DeleteMapping("/{memberId}/watchlists/{watchlistId}")
    @Operation(summary = "Delete watchlist")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content()
            )
    })
    public ResponseEntity<Void> deleteWatchlist(
            @PathVariable Long memberId,
            @PathVariable Long watchlistId
    ) {
        memberService.fetchById(memberId);
        watchlistService.delete(watchlistId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{memberId}/watchlists/{watchlistId}/movies")
    @Operation(summary = "Add the movie to the watchlist")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Added movie to watchlist",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content()
            )
    })
    public ResponseEntity<AddMovieToWatchlistResponse> addMovieToWatchlist(
            @PathVariable Long memberId,
            @PathVariable Long watchlistId,
            @RequestBody @Valid AddMovieToWatchlistRequest addMovieToWatchlistRequest
    ) {
        MovieWatchlist movieWatchlist = movieWatchlistService
                .addMovieToWatchlist(memberId, watchlistId, addMovieToWatchlistRequest.getMovieId());
        return ResponseEntity.ok(AddMovieToWatchlistResponse.valueOf(movieWatchlist));
    }

}
