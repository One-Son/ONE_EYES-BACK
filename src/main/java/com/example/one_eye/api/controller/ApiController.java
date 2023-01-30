package com.example.one_eye.api.controller;

import com.example.one_eye.api.model.Scooter;
import com.example.one_eye.utils.KickGoing;
import com.example.one_eye.config.BaseResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableScheduling
@RestController
@RequestMapping("/api")
public class ApiController {

    private static final long REPEAT_SECOND = 60; //반복 초
    @Autowired
    private ApiService apiService;

    @GetMapping("/")
    public String test(){
        return "hello world";
    }

    @GetMapping("/location")
    public BaseResponse<List<Scooter>> locationScooters(@RequestParam double lat, @RequestParam double lng){
        return new BaseResponse<>(apiService.getScootersByLocation(lat, lng, REPEAT_SECOND));
    }

    @Scheduled(fixedDelay=REPEAT_SECOND * 1000) // 200000초마다 반복
    public void kickGoingApi(){
        double startLat = 37.4770652; // 낙성대역
        double startLng = 126.9634100;
        double endLat = 37.4956227; // 강남역
        double endLng = 127.0271068;
        double distance = 0.0075;

        for(double locationLat = startLat; locationLat < endLat; locationLat += distance) {
            for(double locationLng = startLng; locationLng < endLng; locationLng += distance) {
                apiService.saveScooter(KickGoing.getKickGoingScooter(locationLat, locationLng));
                System.out.printf("거리 차이 %f, %f\n", locationLat - endLat, locationLng - endLng);
            }
        }
    }
}
