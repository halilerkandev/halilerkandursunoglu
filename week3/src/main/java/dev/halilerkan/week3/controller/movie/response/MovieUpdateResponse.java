package dev.halilerkan.week3.controller.movie.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MovieUpdateResponse {

    @Schema(example = "1")
    private Long id;

    public static MovieUpdateResponse valueOf(Long id) {
        return MovieUpdateResponse.builder()
                .id(id)
                .build();
    }

}
