package com.example.one_eye.api.controller;

import com.example.one_eye.api.model.Scooter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
    public List<Scooter> getScootersByLocation(float xCoordinate, float yCooridnate){
        List<Scooter> scooters = new ArrayList<>();

        return scooters;
    }
}
