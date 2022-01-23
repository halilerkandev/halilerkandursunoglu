package dev.halilerkan.week4.dao.rate;

import dev.halilerkan.week4.dao.BaseEntity;
import dev.halilerkan.week4.dao.member.MemberEntity;
import dev.halilerkan.week4.dao.movie.MovieEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "rate")
@Table(name = "rate")
public class RateEntity extends BaseEntity implements Serializable {

    @ManyToOne
    @JoinColumn(
            name = "movie_id",
            referencedColumnName = "id"
    )
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn(
            name = "member_id",
            referencedColumnName = "id"
    )
    private MemberEntity member;

    @Column(nullable = false)
    private Integer point;

}