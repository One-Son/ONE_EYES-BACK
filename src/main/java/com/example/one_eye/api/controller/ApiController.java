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

    @Autowired
    private ApiService apiService;

    @GetMapping("/")
    public String test(){
        return "hello world";
    }

    @GetMapping("/location")
    public BaseResponse<List<Scooter>> locationScooters(@RequestParam Float xCoordinate, @RequestParam Float yCoordinate){
        return new BaseResponse<>(apiService.getScootersByLocation(xCoordinate, yCoordinate));
    }

    @Scheduled(fixedDelay=200000) // 200초마다 반복
    public void kickGoingApi(){
        double startLat = 37.4770652d; // 낙성대역
        double startLng = 126.9634100d;
        double endLat = 37.4806331d; // 사당역
        double endLng = 126.9787347d;
        double distance = 0.0075d;

        for(double locationLat = startLat; locationLat < endLat; locationLat += distance) {
            for(double locationLng = startLng; locationLng < endLng; locationLng += distance) {
                apiService.saveScooter(KickGoing.getKickGoingScooter(locationLat, locationLng));

            }
        }
    }
}
