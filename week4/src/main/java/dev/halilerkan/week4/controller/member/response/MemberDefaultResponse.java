package dev.halilerkan.week4.controller.member.response;

import dev.halilerkan.week4.service.member.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDefaultResponse {

    @Schema(example = "Halil Erkan")
    private String firstName;

    @Schema(example = "Dursunoğlu")
    private String lastName;

    public static MemberDefaultResponse valueOf(Member member) {
        return MemberDefaultResponse.builder()
                .firstName(member.getFirstName())
                .lastName(member.getLastName())
                .build();
    }

}
