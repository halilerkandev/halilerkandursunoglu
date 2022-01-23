package dev.halilerkan.week4.controller.rate;

import dev.halilerkan.week4.controller.rate.response.RateResponse;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RateResponseList {
    private List<RateResponse> rates;

    public RateResponseList() {
        rates = new ArrayList<>();
    }
}
