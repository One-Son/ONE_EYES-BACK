package com.example.one_eye.api.controller;

public enum Location {
    SADANG_GANGNAM(37.47706,126.96341, 37.49562, 127.0271),
    CATHOLIC(37.486253, 126.801699, 37.4862534, 126.8016995),
    //37.485252, 126.775162
    GROOM(37.4024069, 127.1011007, 37.4024069, 127.1011007);
    //37.38891, 127.121509
    private final float startLat;
    private final float startLng;
    private final float endLat;
    private final float endLng;
    Location(double startLat, double startLng, double endLat, double endLng){
        this.startLat = (float) startLat;
        this.startLng = (float) startLng;
        this.endLat = (float) endLat;
        this.endLng = (float) endLng;
    }

    public float getStartLat() {
        return startLat;
    }

    public float getStartLng() {
        return startLng;
    }

    public float getEndLat() {
        return endLat;
    }

    public float getEndLng() {
        return endLng;
    }
}
