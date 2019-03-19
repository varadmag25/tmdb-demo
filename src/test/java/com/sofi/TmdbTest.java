package com.sofi;


import org.junit.Assert;
import org.junit.Test;

public class TmdbTest {


    @Test
    public void requestToken() {
        try {
            MovieDb movieDb = new MovieDb();
           String token =  movieDb.getRequestToken();
            Assert.assertNotNull(token);
        } catch (Exception e) {
            Assert.fail("token request failed");
        }
    }

    @Test
    public void testAuthenticate() {
        try {
            MovieDb movieDb = new MovieDb();
           String token = movieDb.authenticateToken();
            Assert.assertNotNull(token);
        } catch (Exception e) {
            Assert.fail("authentication failed");
        }
    }

    @Test
    public void testGetSession() {
        try {
            MovieDb movieDb = new MovieDb();
            String session = movieDb.getRequestToken();
            Assert.assertNotNull(session);
        } catch (Exception e) {
            Assert.fail("session id failed");
        }
    }}


