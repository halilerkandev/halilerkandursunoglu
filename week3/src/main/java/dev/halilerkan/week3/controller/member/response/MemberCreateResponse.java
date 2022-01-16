package dev.halilerkan.week3.controller.member.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberCreateResponse {

    @Schema(example = "1")
    private Long id;

    public static MemberCreateResponse valueOf(Long id) {
        return MemberCreateResponse.builder()
                .id(id)
                .build();
    }

}
