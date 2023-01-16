package com.example.one_eye.api.controller;

import com.example.one_eye.api.model.Scooter;
import com.example.one_eye.config.BaseResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public BaseResponse<List<Scooter>> locationScooters(@RequestParam float xCoordinate, @RequestParam float yCoordinate){
        return new BaseResponse<>(apiService.getScootersByLocation(xCoordinate, yCoordinate));
    }
}
