package com.example.one_eye.api.repository;

import com.example.one_eye.api.model.Scooter;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long> {

    public List<Scooter> findScooterByLatBetweenAndLngBetween
            (double minusXCoordinate, double plusXCoordinate, double minusYCoordinate, double plusYCooldinate);
}
