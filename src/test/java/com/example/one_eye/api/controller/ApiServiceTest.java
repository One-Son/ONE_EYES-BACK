package com.example.one_eye.api.controller;

import com.example.one_eye.api.model.Scooter;
import com.example.one_eye.api.repository.ScooterRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiServiceTest {

    @Autowired
    private ScooterRepository scooterRepository;

    @Test
    public void 저장_테스트(){
//        Scooter scooter = scooterRepository.findTopByKey("CA7334");
//        Assertions.assertThat(scooter).isNotNull();
    }

}