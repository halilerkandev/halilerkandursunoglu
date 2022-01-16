package dev.halilerkan.week3.controller.member.request;

import dev.halilerkan.week3.service.member.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberCreateRequest {

    @Schema(example = "Halil Erkan")
    @NotBlank
    private String firstName;

    @Schema(example = "Dursunoğlu")
    @NotBlank
    private String lastName;

    public Member toMember() {
        return Member.builder()
                .firstName(getFirstName())
                .lastName(getLastName())
                .build();
    }

}
