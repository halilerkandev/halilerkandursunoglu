package dev.halilerkan.week3.dao.watchlist;

import dev.halilerkan.week3.dao.BaseEntity;
import dev.halilerkan.week3.dao.member.MemberEntity;
import dev.halilerkan.week3.dao.moviewatchlist.MovieWatchlistEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "watchlist")
@Table(name = "watchlist")
public class WatchlistEntity extends BaseEntity implements Serializable {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(
            name = "member_id",
            referencedColumnName = "id"
    )
    private MemberEntity member;

    @OneToMany(mappedBy = "watchlist")
    private List<MovieWatchlistEntity> movies;

}