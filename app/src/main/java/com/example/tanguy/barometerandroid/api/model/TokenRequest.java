package com.example.tanguy.barometerandroid.api.model;

/**
 * Created by Tanguy on 7/05/2018.
 */

public class TokenRequest {
        private String requestVerificationToken;
        private String email;
        private String password;
        private String RememberMe;

        public String getRequestVerificationToken() {
            return requestVerificationToken;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getRememberMe() {
            return RememberMe;
        }

        public void setRequestVerificationToken(String requestVerificationToken) {
            this.requestVerificationToken = requestVerificationToken;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setRememberMe(String rememberMe) {
            RememberMe = rememberMe;
        }

}


