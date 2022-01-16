package dev.halilerkan.week3.controller.member.request;

import dev.halilerkan.week3.service.watchlist.Watchlist;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class WatchlistUpdateRequest {

    @Schema(example = "Latest Movies")
    @NotBlank
    private String name;

    public Watchlist toWatchlist(Long memberId) {
        return Watchlist.builder()
                .name(getName())
                .memberId(memberId)
                .build();
    }

}
