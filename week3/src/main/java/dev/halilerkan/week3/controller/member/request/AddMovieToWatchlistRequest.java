package dev.halilerkan.week3.controller.member.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddMovieToWatchlistRequest {

    @Schema(example = "1")
    @NotNull
    private Long movieId;

}