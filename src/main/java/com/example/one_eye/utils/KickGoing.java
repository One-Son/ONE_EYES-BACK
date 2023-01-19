package com.example.one_eye.utils;

import java.io.IOException;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class KickGoing {
    @Scheduled(fixedDelay=2000) // 2초마다 반복
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
        System.out.println(response.body());
    }
}
