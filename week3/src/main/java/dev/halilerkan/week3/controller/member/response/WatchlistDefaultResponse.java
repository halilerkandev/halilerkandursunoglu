package dev.halilerkan.week3.controller.member.response;

import dev.halilerkan.week3.service.watchlist.Watchlist;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WatchlistDefaultResponse {

    @Schema(example = "Latest Movies")
    private String name;

    @Schema(example = "1")
    private Long memberId;

    public static WatchlistDefaultResponse valueOf(Watchlist watchlist) {
        return WatchlistDefaultResponse.builder()
                .name(watchlist.getName())
                .memberId(watchlist.getMemberId())
                .build();
    }

}
