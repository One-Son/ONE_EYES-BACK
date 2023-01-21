package com.example.one_eye.api.controller;

import com.example.one_eye.api.model.Scooter;
import com.example.one_eye.api.repository.ScooterRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
    @Autowired
    private ScooterRepository scooterRepository;

    /**
     * 사용자의 x좌표, y좌표가 매개 변수로 주어졌을 때
     * range * 2의 길이를 갖는 정사각형 내부에 존재하는 좌표 값을 데이터 베이스에서 찾는다.
    */
    public List<Scooter> getScootersByLocation(Float xCoordinate, Float yCooridnate){
        int range = 100;
        return scooterRepository.findScooterByCoordinateXBetweenAndCoordinateYBetween(
                xCoordinate - range, xCoordinate + range,
                        yCooridnate - range, yCooridnate + range);
    }

    public void saveScooter(List<Scooter> scooters){
        scooterRepository.saveAll(scooters);
    }
}
