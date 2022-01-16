package dev.halilerkan.week3.controller.person.response;

import dev.halilerkan.week3.service.person.Person;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PersonDefaultResponse {

    @Schema(example = "Leonardo")
    private String firstName;

    @Schema(example = "DiCaprio")
    private String lastName;

    public static PersonDefaultResponse valueOf(Person person) {
        return PersonDefaultResponse.builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .build();
    }

}
