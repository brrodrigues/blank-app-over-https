package com.example.demo2;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TesteRepository extends CrudRepository<TesteEntity, Long> {

    Optional<TesteEntity> findById(Long id);

}
