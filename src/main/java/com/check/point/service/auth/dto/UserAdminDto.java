package com.check.point.service.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserAdminDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class New{
        private String id;
        private String firstName;
        private String lastName;
        private String userName;
        private String userPassword;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User {
        private String id;
        private String fullName;
        private String firstName;
        private String lastName;
        private String userName;
        private String userPassword;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Admin {
        private String id;
        private String fullName;
        private String firstName;
        private String lastName;
        private String userName;
        private String userPassword;
    }


}
