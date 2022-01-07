package dev.halilerkan.movie.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Table(name = "actors")
public class ActorEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actorId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(
                    name = "actor_id",
                    referencedColumnName = "actorId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "movie_id",
                    referencedColumnName = "movieId"
            )
    )
    @ToString.Exclude
    private List<MovieEntity> movies;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ActorEntity that = (ActorEntity) o;
        return actorId != null && Objects.equals(actorId, that.actorId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
