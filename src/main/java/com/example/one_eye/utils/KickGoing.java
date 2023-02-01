package com.example.one_eye.utils;

import com.example.one_eye.api.model.Scooter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KickGoing {
    public static List<Scooter> getKickGoingScooter(double lat, double lng) {
        String version = "2.5.9";
        String url = "https://api.kickgoing.io/v4/kickscooters?"
                + "version=%s&lat=%f&lng=%f&zoom=%f";
        double zoom =15.0; // radius = 1500

        Response response = connectApi(String.format(url, version, lat, lng, zoom));
        JSONObject api = parseJson(response.body());

        if(api != null) {
            return parseKickScooters(parseJsonArray(api.get("kickscooters").toString()));
        }
        return null;
    }

    private static Response connectApi(String url){
        try {
            return Jsoup.connect(url)
                .header("content-type", "application/json")
                .header("Accept-Encoding", "gzip, deflate")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.1 Safari/605.1.15")
                .ignoreContentType(true).execute();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static JSONArray parseJsonArray(String str){
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = null;
        try{
            jsonArray = (JSONArray) jsonParser.parse(str);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return jsonArray;
    }

    private static JSONObject parseJson(String str){
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try{
            jsonObject = (JSONObject) jsonParser.parse(str);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return jsonObject;
    }

    private static List<Scooter> parseKickScooters(JSONArray kickScooters){
        List<Scooter> scooters = new ArrayList<>();
        for (Object kickScooter: kickScooters) {
            JSONObject detail = parseJson(kickScooter.toString());
            Scooter scooter = new Scooter(
                    detail.get("serial_number").toString(),
                    detail.get("lat").toString(),
                    detail.get("lng").toString()
            );

            // sout 찍어보기
//            System.out.printf("serial_number: %s, lat: %f, lng: %f\n",
//                    detail.get("serial_number"), detail.get("lat"), detail.get("lng"));
            scooters.add(scooter);
        }
        return scooters;
    }
}
