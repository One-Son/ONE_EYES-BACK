package com.example.one_eye.api.controller;

import com.example.one_eye.api.model.Scooter;
import com.example.one_eye.api.repository.ScooterRepository;
import java.time.LocalDateTime;
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
    public List<Scooter> getScootersByLocation(double lat, double lng, long repeatSecond){
        double range = 0.01; // 0.01 = 약 1000m
        return scooterRepository.findScooterByLatBetweenAndLngBetweenAndUpdateAtAfter(
                lat - range, lat + range,
                        lng - range, lng + range,
                LocalDateTime.now().minusSeconds(repeatSecond));
    }

    /**
     * 기존 테이블에 시리얼이 존재하면 Lat, Lng UPDATE
     * 존재하지 않으면 INSERT
     */
    public void saveScooter(List<Scooter> scooters){
        for(Scooter scooter: scooters) {
            Scooter existScooter = scooterRepository.findTopByKey(scooter.getKey());
            if(existScooter != null){
                existScooter.setLat(scooter.getLat());
                existScooter.setLng(scooter.getLng());
                scooterRepository.save(existScooter);
            }
            else{
                scooterRepository.save(scooter);
            }
        }
    }
}
