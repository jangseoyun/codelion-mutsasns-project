package com.codelion.mutsasns.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    private String resultCode;
    private T result;

    public static <T> Response<T> error(String resultCode, T result) {
        return new Response("ERROR", result);
    }

    public static <T> Response<T> success(T result) {
        return new Response("SUCCESS", result);
    }

}
