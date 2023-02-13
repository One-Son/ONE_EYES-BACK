package com.example.one_eye.api.controller;

import com.example.one_eye.api.model.Scooter;
import com.example.one_eye.utils.KickGoing;
import com.example.one_eye.config.BaseResponse;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableScheduling
@RestController
@Slf4j
@RequestMapping("/api")
public class ApiController {

    private static final long REPEAT_SECOND = 600; //반복 초
    @Autowired
    private ApiService apiService;

    @GetMapping("/location/admin")
    public BaseResponse<List<Scooter>> adminLocationScooters(){
        return new BaseResponse<>(apiService.getAdminLocation(REPEAT_SECOND));
    }

    @GetMapping("/location")
    public BaseResponse<List<Scooter>> locationScooters(@RequestParam float lat, @RequestParam float lng){
        return new BaseResponse<>(apiService.getScootersByLocation(lat, lng, REPEAT_SECOND));
    }

    @Scheduled(fixedDelay=REPEAT_SECOND * 1000) // 200000초마다 반복
    public void kickGoingApi(){
        log.info("start");
        int count = 0;
        for (Location location: Location.values()) {
            count += saveScooters(location);
        }
        log.info("DB update count = " + count);
    }

    public int saveScooters(Location location){
        float distance = 0.022f;
        int count = 0;

        for(float locationLat = location.getStartLat(); locationLat <= location.getEndLat(); locationLat += distance * 2) {
            for(float locationLng = location.getStartLng(); locationLng <= location.getEndLng(); locationLng += distance * 2) {
                count += apiService.saveScooter(KickGoing.getKickGoingScooter(locationLat, locationLng));
            }
        }
        for(float locationLat = location.getStartLat() + distance; locationLat <= location.getEndLat() + distance; locationLat += distance * 2) {
            for(float locationLng = location.getStartLng() + distance; locationLng <= location.getEndLng() + distance; locationLng += distance * 2) {
                count += apiService.saveScooter(KickGoing.getKickGoingScooter(locationLat, locationLng));
            }
        }
        return count;
    }
}
