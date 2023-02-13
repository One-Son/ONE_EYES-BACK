package com.example.one_eye.api.controller;

import com.example.one_eye.api.model.Scooter;
import com.example.one_eye.api.repository.ScooterRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ApiService {
    @Autowired
    private ScooterRepository scooterRepository;

    /**
     * 사용자의 x좌표, y좌표가 매개 변수로 주어졌을 때
     * range * 2의 길이를 갖는 정사각형 내부에 존재하는 좌표 값을 데이터 베이스에서 찾는다.
    */
    public List<Scooter> getScootersByLocation(float lat, float lng, long repeatSecond){
        float range = 0.0025f; // 0.01 = 약 1000m
        return scooterRepository.findScooterByLatBetweenAndLngBetweenAndUpdateAtAfter(
                Double.toString(lat - range), Double.toString(lat + range),
                Double.toString(lng - range), Double.toString(lng + range),
                LocalDateTime.now().minusSeconds(repeatSecond * 3).plusHours(9));
    }

    /**
     * 어드민 페이지
     */
    public List<Scooter> getAdminLocation(long repeatSecond){
        List<Scooter> s = scooterRepository.findScooterByUpdateAtAfter(LocalDateTime.now().minusSeconds(repeatSecond * 3).minusHours(9));
        log.info(LocalDateTime.now().minusSeconds(repeatSecond * 3).minusHours(9).toString());
        return scooterRepository.findScooterByUpdateAtAfter(LocalDateTime.now().minusSeconds(repeatSecond * 3).minusHours(9));
    }

    /**
     * 기존 테이블에 시리얼이 존재하면 Lat, Lng UPDATE
     * 존재하지 않으면 INSERT
     */
    public int saveScooter(List<Scooter> scooters){
        int count = 0;
        for(Scooter scooter: scooters) {
            Scooter existScooter = scooterRepository.findTopByKey(scooter.getKey());
            if(existScooter != null){
                existScooter.setLat(scooter.getLat());
                existScooter.setLng(scooter.getLng());
                scooterRepository.save(existScooter);
                count += 1;
            }
            else{
                scooterRepository.save(scooter);
            }
            //log.info(scooter.getLat() + " " + scooter.getLng() + "\n");
        }
        return count;
    }
}
