package com.sofi;


import lombok.Data;

import org.apache.http.util.EntityUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;


import java.io.IOException;

import java.nio.charset.StandardCharsets;


@Data
public class MovieDb {

   private Config config = new Config();

private static String TRUE = "true";


   public String getRequestToken() throws IOException {
       HttpClient client = new DefaultHttpClient() {
       };
       HttpGet get = new HttpGet(config.getBaseUrl() + "/3/authentication/token/new?api_key=" + config.getApikey());
       get.setHeader("Content-type", "application/json");
       HttpResponse response = client.execute(get);
       HttpEntity entity = response.getEntity();

       String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);

       JSONObject object = new JSONObject(json);
       if (object.get("success").toString().equals(TRUE)) {
           config.setTokenID(object.get("request_token").toString());
           return object.get("request_token").toString();
       }
       return null;
    }

    public String authenticateToken() throws IOException {
       getRequestToken();
       String token = config.getTokenID();
        HttpClient client = new DefaultHttpClient();

        String pass="{\"username\":\""+ config.getUsername() +"\",\"password\":\""+ config.getPassword() + "\",\"request_token\":\""+token+"\"}";

        HttpPost post = new HttpPost(config.getBaseUrl()  + "/3/authentication/token/validate_with_login?api_key="+config.getApikey() );

        post.setHeader("Content-type", "application/json");
        StringEntity userEntity = new StringEntity(pass);
        post.setEntity(userEntity);

        HttpResponse response = client.execute(post);
        HttpEntity entity = response.getEntity();

        String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);

        JSONObject object = new JSONObject(json);
        if (object.get("success").toString().equals(TRUE)) {
            config.setTokenID(object.get("request_token").toString());
            return object.get("request_token").toString();
        }
        return null;

    }

    public String getSession() throws IOException {
        HttpClient client = new DefaultHttpClient();
        String token = authenticateToken();
        String pass = "{\"request_token\":\"" + token + "\"}";

        HttpPost post = new HttpPost(config.getBaseUrl() + "/3/authentication/session/new?api_key=" + config.getApikey());

        post.setHeader("Content-type", "application/json");
        StringEntity userEntity = new StringEntity(pass);
        post.setEntity(userEntity);

        HttpResponse response = client.execute(post);
        HttpEntity entity = response.getEntity();

        String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);

        JSONObject object = new JSONObject(json);
        if (object.get("success").toString().equals(TRUE)) {
            config.setSessionID(object.get("session_id").toString());
            return object.get("session_id").toString();

        }
      return null;
    }

}
