package com.check.point.service.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

public class UserAdminDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class New{
        private String id;
        private String firstName;
        private String lastName;
        private String fullName;
        private String userName;
        private String userPassword;
        private String gender;
        private String email;
        private String phoneNumber;
        private Date birthDate;
        private Timestamp registerTime;
        private String accountStatus;
        private String pin;
        private String photoProfile;
        private String path;
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
    public static class Update {
        private String id;
        private String fullName;
        private String firstName;
        private String lastName;
        private String userName;
        private String status;
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
