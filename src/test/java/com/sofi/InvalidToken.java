package com.sofi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class InvalidToken {

    String session;
    Config config;

    @Before
    public void authenticate() throws IOException {
        MovieDb movieDb = new MovieDb();
        session = movieDb.getSession();
        config = movieDb.getConfig();
        RestAssured.baseURI=config.getBaseUrl();
    }

    @Test

    public void invalidtoken (){

        String requestBody = "{\"username\":\"" + "INVALID" + "\",\"password\":\"" + config.getPassword() + "\",\"request_token\":\"" + "1616565265252222" + "\"}";
        ExtractableResponse<Response> resp = given().contentType(ContentType.JSON)
                .body(requestBody).post("3/authentication/token/validate_with_login?api_key=" + config.getApikey())
                .then().statusCode(401).extract();
        Assert.assertEquals(401, ((RestAssuredResponseImpl) resp).getStatusCode());

    }

}
