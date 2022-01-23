package dev.halilerkan.week4.controller.person.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonCreateResponse {

    @Schema(example = "1")
    private Long id;

    public static PersonCreateResponse valueOf(Long id) {
        return PersonCreateResponse.builder()
                .id(id)
                .build();
    }

}
