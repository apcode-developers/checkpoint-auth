package com.check.point.service.auth.controller;

import com.check.point.service.auth.datatable.DataTableRequest;
import com.check.point.service.auth.datatable.DataTableResponse;
import com.check.point.service.auth.dto.UserAdminDto;
import com.check.point.service.auth.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api/user-admin")
public class UserAdminController {

    @Autowired
    private UserAdminService service;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        try {
            service.delete(id);
            return new ResponseEntity<>(null, HttpStatus.OK);

        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/data/user")
    public ResponseEntity<?> getDataUser(@RequestParam String username, @RequestParam String password){
        try{
            Optional<UserAdminDto.New> arsip = service.getDataUser(username, password);
            return new ResponseEntity<>(arsip, HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/datatable")
    public ResponseEntity<DataTableResponse<UserAdminDto.User>> getArsip(@RequestBody DataTableRequest dataTableRequest){
        return ResponseEntity.ok().body(service.datatables(dataTableRequest));
    }
}
