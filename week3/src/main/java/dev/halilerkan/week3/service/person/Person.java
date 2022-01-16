package dev.halilerkan.week3.service.person;

import dev.halilerkan.week3.dao.person.PersonEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Person {

    private Long id;
    private String firstName;
    private String lastName;

    PersonEntity toPersonEntity() {
        return PersonEntity.builder()
                .firstName(getFirstName())
                .lastName(getLastName())
                .build();
    }

    public static Person valueOf(PersonEntity entity) {
        return Person.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .build();
    }

}
