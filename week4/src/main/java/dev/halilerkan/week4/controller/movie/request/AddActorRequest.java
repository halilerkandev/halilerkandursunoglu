package dev.halilerkan.week4.controller.movie.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddActorRequest {

    @Schema(example = "1")
    @NotNull
    private Long actorId;

}
