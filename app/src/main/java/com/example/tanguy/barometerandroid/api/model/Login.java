package com.example.tanguy.barometerandroid.api.model;

/**
 * Created by Tanguy on 8/05/2018.
 */

public class Login {
    private String username;
    private String password;
    //private String grant_type;

    public Login(String user, String password) {
        this.username = user;
        this.password = password;
        //this.grant_type = "password";
    }
}
