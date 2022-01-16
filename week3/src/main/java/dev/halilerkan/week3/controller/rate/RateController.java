package dev.halilerkan.week3.controller.rate;

import dev.halilerkan.week3.controller.rate.request.RateRequest;
import dev.halilerkan.week3.controller.rate.response.RateResponse;
import dev.halilerkan.week3.service.rate.Rate;
import dev.halilerkan.week3.service.rate.RateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rates")
@RequiredArgsConstructor
@Tag(name = "Rates")
public class RateController {

    private final RateService rateService;

    @PostMapping
    @Operation(summary = "Rate movie")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Movie rated"
            )
    })
    public ResponseEntity<Void> rateMovie(
            @RequestBody @Valid RateRequest rateRequest
    ) {
        Rate rate = rateRequest.toRate();
        rateService.rateMovie(rate);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    @Operation(summary = "Fetch all movie rates")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Movie rates fetched"
            )
    })
    public ResponseEntity<List<RateResponse>> fetchAll(
            @RequestParam Long movieId
    ) {
        List<Rate> rateList = rateService.fetchByMovieId(movieId);

        return ResponseEntity.ok(
                rateList.stream()
                        .map(RateResponse::valueOf)
                        .collect(Collectors.toList())
        );
    }

}
