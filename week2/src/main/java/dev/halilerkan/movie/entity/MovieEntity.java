package dev.halilerkan.movie.entity;

import dev.halilerkan.movie.entity.enumeration.Genre;
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
@Table(
        name = "movies",
        uniqueConstraints = @UniqueConstraint(
                name = "name_unique",
                columnNames = "name"
        )
)
public class MovieEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(nullable = false)
    private Integer releaseYear;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "director_id",
            referencedColumnName = "directorId"
    )
    private DirectorEntity director;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(
                    name = "movie_id",
                    referencedColumnName = "movieId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "actor_id",
                    referencedColumnName = "actorId"
            )
    )
    @ToString.Exclude
    private List<ActorEntity> cast;

    public void addActor(ActorEntity actorEntity) {
        if(cast == null) cast = new ArrayList<>();
        cast.add(actorEntity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MovieEntity that = (MovieEntity) o;
        return movieId != null && Objects.equals(movieId, that.movieId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

