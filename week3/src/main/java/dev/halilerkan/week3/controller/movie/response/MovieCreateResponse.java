package dev.halilerkan.week3.controller.movie.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MovieCreateResponse {

    @Schema(example = "1")
    private Long id;

    public static MovieCreateResponse valueOf(Long id) {
        return MovieCreateResponse.builder()
                .id(id)
                .build();
    }

}
