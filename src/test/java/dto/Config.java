package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Config {

    private String url = "https://api.themoviedb.org";

    private String tokenID;

    private String sessionID;
    private String userId;

    private Boolean status;

    private int stCode;


}
