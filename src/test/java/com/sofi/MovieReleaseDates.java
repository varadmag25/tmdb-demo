package com.sofi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;





    public class MovieReleaseDates {

        Config config;

        @Before
        public void authenticate() {
            MovieDb movieDb = new MovieDb();
            config = movieDb.getConfig();
            RestAssured.baseURI = "https://api.themoviedb.org/3";
        }

        @Test
        public void test_ValidMovieReviews() {

            int movieId = 100;
            Response response = given().get("/movie/{movie_id}release_dates?api_key={apiKey}", movieId, config.getApikey());
            Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        }

        @Test
        public void test_InValidMovieReviwes() {

            String movieId = "INVALID";
            Response response = given().get("/movie/{movie_id}/release_dates ?api_key={apiKey}", movieId, config.getApikey());
            Assert.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());
        }

    }

