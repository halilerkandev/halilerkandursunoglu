package dev.halilerkan.week4.controller.member.request;

import dev.halilerkan.week4.service.watchlist.Watchlist;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class WatchlistCreateRequest {

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
