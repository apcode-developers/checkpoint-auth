package com.check.point.service.auth.controller;


import com.check.point.service.auth.dto.UserAdminDto;
import com.check.point.service.auth.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@RequestMapping(path = "/api/regis")
public class RegisterController {

    @Autowired
    private RegisterService service;

    @PostMapping(path = "/save")
    public ResponseEntity<?> saveAdmin(@RequestBody UserAdminDto.New newAkun){
        try{
            service.saveAdmin(newAkun);
            return new ResponseEntity<>(newAkun, HttpStatus.CREATED);
        } catch (SQLException e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/verify")
    public ResponseEntity<?> verify(@RequestBody UserAdminDto.New newAkun){
        try{
            service.verify(newAkun);
            return new ResponseEntity<>(newAkun, HttpStatus.CREATED);
        } catch (DataAccessException e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
