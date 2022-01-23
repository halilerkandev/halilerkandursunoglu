package dev.halilerkan.week4.dao.movieactor;

import dev.halilerkan.week4.dao.BaseEntity;
import dev.halilerkan.week4.dao.movie.MovieEntity;
import dev.halilerkan.week4.dao.person.PersonEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "movie_actor")
@Table(name = "movie_actor")
public class MovieActorEntity extends BaseEntity implements Serializable {

    @ManyToOne
    @JoinColumn(
            name = "movie_id",
            referencedColumnName = "id"
    )
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn(
            name = "actor_id",
            referencedColumnName = "id"
    )
    private PersonEntity actor;

}