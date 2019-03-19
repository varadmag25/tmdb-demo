package com.sofi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Authentication {
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
    public void getAccountID() {
        ExtractableResponse<Response> resp = given().contentType(ContentType.JSON).get("3/account?api_key=" + config.getApikey() + "&session_id=" + config.getSessionID()).then().statusCode(200).extract();
        JsonPath jsondata = resp.jsonPath();
        config.setUserId(Integer.toString(jsondata.get("id")));
        Assert.assertEquals(jsondata.get("username"), config.getUsername());
        Assert.assertNotNull(jsondata.get("include_adult"));
        Assert.assertNotNull(jsondata.get("name"));
    }




}
