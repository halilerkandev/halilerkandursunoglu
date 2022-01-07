package dev.halilerkan.movie.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Table(name = "ratings")
public class RatingEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;

    @ManyToOne(
            cascade = CascadeType.ALL,
            optional = false
    )
    @JoinColumn(
            name = "movie_id",
            referencedColumnName = "movieId"
    )
    private MovieEntity movie;

    @ManyToOne(
            cascade = CascadeType.ALL,
            optional = false
    )
    @JoinColumn(
            name = "member_id",
            referencedColumnName = "memberId"
    )
    private MemberEntity member;

    @Column(nullable = false)
    @Min(value = 1 , message = "Point should be greater then then equal to 1")
    @Max(value = 5 , message = "Point should be less then then equal to 5")
    private Integer point;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RatingEntity that = (RatingEntity) o;
        return ratingId != null && Objects.equals(ratingId, that.ratingId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
