package com.spotify.oauth2.api;

import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {
    public static Response POST(String path, String token, Object requestPlaylist) {
        return given(getRequestSpecification()).
                body(requestPlaylist).
                auth().oauth2(token).
                when().post(path).
                then().spec(getResponseSpecification()).
                extract().
                response();
    }

    public static Response postAccount(HashMap<String, String> formParams) {
        return given(getAccountRequestSpecification()).
                formParams(formParams).
                when().post("/api/token").
                then().spec(getResponseSpecification()).
                extract().response();
    }

    public static Response GET(String path, String token) {
        return given(getRequestSpecification()).
                auth().oauth2(token).
                when().get(path).
                then().spec(getResponseSpecification()).
                extract().
                response();
    }

    public static Response PUT(String path, String token, Object requestPlaylist) {
        return given(getRequestSpecification()).
                body(requestPlaylist).
                auth().oauth2(token).
                when().put(path).
                then().spec(getResponseSpecification()).
                extract().response();
    }
}
