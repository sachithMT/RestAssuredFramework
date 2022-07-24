package com.spotify.oauth2.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

   static String access_token = "BQAAnpg2GouudZM9ko-jKYnnTRU5RzyNGLaQaVdAFXbCDqBGd4mMDG_RgeCXwR2UMYvhIhM1z1lUaVjqCl6itx0lWJzAxOFQUHhRgzaLcFqLNG4jsy0trMSAcg10ZltqVygLc7EH2UcPCCbv5918lxHS2JVAHWUb4ypwWHIUUHCjSyRsnF7TGcemmYYZWVaqopH_EXTs85yZzLIbqD9DtMbIdDj-qBR6ypSkh0YOYYp0DXS6gHo2Bd1JXZdCJHExlSNWCWIqMALS3WQa";

    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder().
                setBaseUri(System.getProperty("BASE_URI")).
                //setBaseUri("https://api.spotify.com").
                setBasePath("/v1").
                setContentType(ContentType.JSON).
                addFilter(new AllureRestAssured()).
                log(LogDetail.ALL).build();
    }

    public static RequestSpecification getAccountRequestSpecification() {
        return new RequestSpecBuilder().
                setBaseUri(System.getProperty("ACCOUNT_BASE_URI")).
                //setBaseUri("https://accounts.spotify.com").
                setContentType(ContentType.URLENC).
                addFilter(new AllureRestAssured()).
                log(LogDetail.ALL).
                build();
    }

    public static ResponseSpecification getResponseSpecification() {
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
    }

}
