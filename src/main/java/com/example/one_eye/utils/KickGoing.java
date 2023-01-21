package com.example.one_eye.utils;

import java.io.IOException;

import com.example.one_eye.api.model.Scooter;
import com.example.one_eye.api.repository.ScooterRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import java.lang.Float;

@SpringBootApplication
@EnableScheduling
public class KickGoing {
    @Scheduled(fixedDelay=200000) // 200초마다 반복
    public void getKickGoingScooter() {
        String url = "https://api.kickgoing.io/v4/kickscooters?"
                + "version=%s&lat=%f&lng=%f&zoom=%f";
        Response response = null;

        try {
            String version = "2.5.9";
            double lat = 37.5;
            double lng = 126.9;
            double zoom = 15.1;
            response = Jsoup.connect(String.format(url, version, lat, lng, zoom))
                    .header("content-type", "application/json")
                    .header("Accept-Encoding", "gzip, deflate")
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.1 Safari/605.1.15")
                    .ignoreContentType(true).execute();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        JSONObject api = parseJson(response.body());
        if(api != null) {
            JSONArray kickScooters = parseJsonArray(api.get("kickscooters").toString());
            for (Object kickScooter: kickScooters) {
                JSONObject detail = parseJson(kickScooter.toString());
                System.out.printf("serial_number: %s, lat: %f, lng: %f\n",
                        detail.get("serial_number"), detail.get("lat"), detail.get("lng")); // get안에
                saveScooter(
                        String.valueOf(detail.get("serial_number")),
                        Float.valueOf(String.valueOf(detail.get("lat"))),
                        Float.valueOf(String.valueOf(detail.get("lng")))
                );

            }
        }
    }

    @Autowired
    ScooterRepository scooterRepository;
    private void saveScooter(String serial_number, Float lat, Float lng){

        Scooter scooter = new Scooter();
        scooter.setKey(serial_number);
        scooter.setCoordinateX(lng);
        scooter.setCoordinateY(lat);
        this.scooterRepository.save(scooter);
    }

    private JSONArray parseJsonArray(String str){
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = null;
        try{
            jsonArray = (JSONArray) jsonParser.parse(str);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return jsonArray;
    }

    private JSONObject parseJson(String str){
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try{
            jsonObject = (JSONObject) jsonParser.parse(str);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return jsonObject;
    }
}
