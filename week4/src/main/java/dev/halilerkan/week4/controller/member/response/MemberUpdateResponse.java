package dev.halilerkan.week4.controller.member.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberUpdateResponse {

    @Schema(example = "1")
    private Long id;

    public static MemberUpdateResponse valueOf(Long id) {
        return MemberUpdateResponse.builder()
                .id(id)
                .build();
    }

}
