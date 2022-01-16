package dev.halilerkan.week3.dao.movie;

import dev.halilerkan.week3.common.enumeration.MovieGenre;
import dev.halilerkan.week3.dao.BaseEntity;
import dev.halilerkan.week3.dao.movieactor.MovieActorEntity;
import dev.halilerkan.week3.dao.person.PersonEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "movie")
@Table(
        name = "movie",
        uniqueConstraints = @UniqueConstraint(
                name = "name_unique",
                columnNames = "name"
        )
)
public class MovieEntity extends BaseEntity implements Serializable {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MovieGenre genre;

    @Column(nullable = false)
    private Integer releaseYear;

    @ManyToOne
    @JoinColumn
    private PersonEntity director;

    @OneToMany(mappedBy = "movie")
    private List<MovieActorEntity> cast;

}
