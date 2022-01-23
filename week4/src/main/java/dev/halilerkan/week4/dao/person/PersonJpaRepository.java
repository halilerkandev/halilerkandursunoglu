package dev.halilerkan.week4.dao.person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonJpaRepository extends JpaRepository<PersonEntity, Long> {}
