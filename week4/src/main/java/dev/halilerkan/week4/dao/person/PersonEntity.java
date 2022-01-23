package dev.halilerkan.week4.dao.person;

import dev.halilerkan.week4.dao.BaseEntity;
import dev.halilerkan.week4.dao.movie.MovieEntity;
import dev.halilerkan.week4.dao.movieactor.MovieActorEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "person")
@Table(name = "person")
public class PersonEntity extends BaseEntity implements Serializable {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "director")
    private List<MovieEntity> directedMovies;

    @OneToMany(mappedBy = "actor")
    private List<MovieActorEntity> playedMovies;
}
