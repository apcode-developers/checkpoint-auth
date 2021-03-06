package com.check.point.service.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusLogin {

    private String userName;
    private String fullName;
    private String token;
    private Boolean isValid;
    private List<String> roles;

}

