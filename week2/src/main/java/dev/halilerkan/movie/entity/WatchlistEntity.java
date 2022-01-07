package dev.halilerkan.movie.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Table(name = "watchlists")
public class WatchlistEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long watchlistId;

    @Column(nullable = false)
    private String name;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "member_id",
            referencedColumnName = "memberId"
    )
    private MemberEntity member;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "movie_watchlist",
            joinColumns = @JoinColumn(
                    name = "watchlist_id",
                    referencedColumnName = "watchlistId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "movie_id",
                    referencedColumnName = "movieId"
            )
    )
    @ToString.Exclude
    private List<MovieEntity> movies;

    public void addMovie(MovieEntity movieEntity) {
        if(movies == null) movies = new ArrayList<>();
        movies.add(movieEntity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        WatchlistEntity that = (WatchlistEntity) o;
        return watchlistId != null && Objects.equals(watchlistId, that.watchlistId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
