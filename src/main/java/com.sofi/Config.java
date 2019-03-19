package com.sofi;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Config {

    private String baseUrl  = "https://api.themoviedb.org";
    private String apikey = "e91a355e30044f2907cb2fbf726b99f6";
    private String username = "varadmag25";
    private String password = "sofitesting";
    private String tokenID;
    private String sessionID;
    private String userId;
    private Boolean status;
    private int stCode;

}
