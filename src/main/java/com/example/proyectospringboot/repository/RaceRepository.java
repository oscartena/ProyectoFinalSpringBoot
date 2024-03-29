package com.example.proyectospringboot.repository;

import com.example.proyectospringboot.entity.Race;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RaceRepository extends JpaRepository<Race, Long> {

    Optional<Race> findByRound(int round);
    void deleteByRound(int round);

    Page<Race> findAllProjectedBy(Pageable pageable);
}
