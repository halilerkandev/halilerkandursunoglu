package dev.halilerkan.week3.controller.person.request;

import dev.halilerkan.week3.service.person.Person;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PersonUpdateRequest {

    @Schema(example = "Leonardo")
    @NotBlank
    private String firstName;

    @Schema(example = "DiCaprio")
    @NotBlank
    private String lastName;

    public Person toPerson() {
        return Person.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

}
