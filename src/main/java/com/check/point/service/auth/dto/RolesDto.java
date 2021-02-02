package com.check.point.service.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class RolesDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User{
        private String id;
        private String namaRole;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserAdmin{
        private String id;
        private String namaRole;
    }
}
