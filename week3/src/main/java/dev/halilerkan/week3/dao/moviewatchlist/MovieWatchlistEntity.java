package dev.halilerkan.week3.dao.moviewatchlist;

import dev.halilerkan.week3.dao.BaseEntity;
import dev.halilerkan.week3.dao.movie.MovieEntity;
import dev.halilerkan.week3.dao.watchlist.WatchlistEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "movie_watchlist")
@Table(name = "movie_watchlist")
public class MovieWatchlistEntity extends BaseEntity implements Serializable {

    @ManyToOne
    @JoinColumn(
            name = "movie_id",
            referencedColumnName = "id"
    )
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn(
            name = "watchlist_id",
            referencedColumnName = "id"
    )
    private WatchlistEntity watchlist;

}
