package com.example.tanguy.barometerandroid.api.model;

/**
 * Created by Tanguy on 8/05/2018.
 */

public class Login {
    private String username;
    private String password;
    private String grant_type;

    public Login(String username, String password, String grant_type) {
        this.username = username;
        this.password = password;
        this.grant_type = grant_type;
    }

}
