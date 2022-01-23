package dev.halilerkan.week4.service.member;

import dev.halilerkan.week4.dao.member.MemberEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Member {

    private String firstName;
    private String lastName;

    MemberEntity toMemberEntity() {
        return MemberEntity.builder()
                .firstName(getFirstName())
                .lastName(getLastName())
                .build();
    }

    public static Member valueOf(MemberEntity memberEntity) {
        return Member.builder()
                .firstName(memberEntity.getFirstName())
                .lastName(memberEntity.getLastName())
                .build();
    }

}
