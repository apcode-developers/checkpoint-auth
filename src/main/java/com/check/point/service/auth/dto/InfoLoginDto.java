package com.check.point.service.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class InfoLoginDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User{
        private String id;
        private String userName;
        private String userPassword;
        private String token;
        private String as;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserAdmin{
        private String id;
        private String userName;
        private String userPassword;
        private String token;
        private String as;
    }
}
