package dev.halilerkan.week4.service.rate;

import dev.halilerkan.week4.dao.member.MemberEntity;
import dev.halilerkan.week4.dao.movie.MovieEntity;
import dev.halilerkan.week4.dao.rate.RateEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Rate {

    private Long memberId;
    private Long movieId;
    private Integer point;

    public RateEntity toRateEntity(MovieEntity movieEntity, MemberEntity memberEntity) {
        RateEntity entity = new RateEntity();
        entity.setMovie(movieEntity);
        entity.setMember(memberEntity);
        entity.setPoint(point);
        return entity;
    }

    public static Rate valueOf(RateEntity entity) {
        return Rate.builder()
                .memberId(entity.getMember().getId())
                .movieId(entity.getMovie().getId())
                .point(entity.getPoint())
                .build();
    }
}
