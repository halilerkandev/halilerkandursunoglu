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
@Table(name = "directors")
public class DirectorEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long directorId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "director_id",
            referencedColumnName = "directorId"
    )
    @ToString.Exclude
    private List<MovieEntity> movies;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DirectorEntity that = (DirectorEntity) o;
        return directorId != null && Objects.equals(directorId, that.directorId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
