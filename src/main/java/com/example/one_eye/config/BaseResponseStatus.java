package com.example.one_eye.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 100 : 요청 성공
     */
    SUCCESS(true, 100, "요청에 성공하였습니다."),

    /**
     * 400 : Database, Server 오류
     */
    DATABASE_ERROR(false, 400, "데이터베이스 연결에 실패하였습니다.");


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) { //BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
