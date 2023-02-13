package com.example.one_eye.api.controller;

public enum Location {
    SADANG_GANGNAM(37.4770652,126.9634100, 37.4956227, 127.0271068),
    CATHOLIC(126.8016995, 37.4862534, 126.8016995, 37.4862534);

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
