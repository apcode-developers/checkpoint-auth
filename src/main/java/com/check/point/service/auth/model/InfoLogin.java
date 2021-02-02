package com.check.point.service.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoLogin {

    private String id;
    private String userName;
    private String token;
    private String as;

}
