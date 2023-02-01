package com.example.one_eye.api.repository;

import com.example.one_eye.api.model.Scooter;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long> {

    List<Scooter> findScooterByLatBetweenAndLngBetweenAndUpdateAtAfter
            (String minusXCoordinate, String plusXCoordinate, String minusYCoordinate, String plusYCooldinate, LocalDateTime timeAfter);
    Scooter findTopByKey(String key);
}
