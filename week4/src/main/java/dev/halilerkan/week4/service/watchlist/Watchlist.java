package dev.halilerkan.week4.service.watchlist;

import dev.halilerkan.week4.dao.member.MemberEntity;
import dev.halilerkan.week4.dao.watchlist.WatchlistEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Watchlist {

    private String name;
    private Long memberId;

    WatchlistEntity toWatchlistEntity(MemberEntity memberEntity) {
        return WatchlistEntity.builder()
                .name(getName())
                .member(memberEntity)
                .build();
    }

    public static Watchlist valueOf(WatchlistEntity watchlistEntity) {
        return Watchlist.builder()
                .name(watchlistEntity.getName())
                .memberId(watchlistEntity.getMember().getId())
                .build();
    }

}
