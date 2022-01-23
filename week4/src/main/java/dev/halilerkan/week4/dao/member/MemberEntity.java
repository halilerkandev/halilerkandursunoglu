package dev.halilerkan.week4.dao.member;

import dev.halilerkan.week4.dao.BaseEntity;
import dev.halilerkan.week4.dao.watchlist.WatchlistEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "member")
@Table(name = "member")
public class MemberEntity extends BaseEntity implements Serializable {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "member")
    private List<WatchlistEntity> watchlists;

}
