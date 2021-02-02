package com.check.point.service.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class GroupUserDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NewRegister{

        private String id;
        private String idUser;
        private Integer idGruop;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User{

        private String id;
        private String idUser;
        private Integer idGruop;
        private String username;
        private String roleName;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserAdmin{

        private String id;
        private String idUser;
        private Integer idGruop;
        private String username;
        private String roleName;
    }




}
