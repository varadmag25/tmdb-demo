package com.sofi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class MovieDetails {

    Config config;

    @Before
    public void authenticate() {
        MovieDb movieDb = new MovieDb();
        config = movieDb.getConfig();
        RestAssured.baseURI = "https://api.themoviedb.org/3";
    }

    @Test
    public void test_ValidMovieDetails() {

        int movieId = 300;
        Response response = given().get("/movie/{movie_id}?api_key={apiKey}", movieId, config.getApikey());
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    }

    @Test
    public void test_InValidMovieDetails() {

        String movieId = "INVALID";
        Response response = given().get("/movie/{movie_id}?api_key={apiKey}", movieId, config.getApikey());
        Assert.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());
    }







}
