package com.sofi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class InvalidPassword {

    Config config;

    @Before
    public void authenticate() {
        MovieDb movieDb = new MovieDb();
        config = movieDb.getConfig();
        RestAssured.baseURI = config.getBaseUrl();
    }

    @Test
    public void invalidpassword() {
        String requestBody = "{\"username\":\"" + config.getUsername() + "\",\"password\":\"" + "xyzzzz" + "\",\"request_token\":\"" + config.getTokenID() + "\"}";
        ExtractableResponse<Response> resp = given().contentType(ContentType.JSON)
                .body(requestBody).post("3/authentication/token/validate_with_login?api_key=" + config.getApikey())
                .then().statusCode(401).extract();
        Assert.assertEquals(401, ((RestAssuredResponseImpl) resp).getStatusCode());

    }
}
