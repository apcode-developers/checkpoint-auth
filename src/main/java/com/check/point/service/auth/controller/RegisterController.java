package com.check.point.service.auth.controller;


import com.check.point.service.auth.dto.UserAdminDto;
import com.check.point.service.auth.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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

    @PutMapping(path = "/photo-set")
    public ResponseEntity<?> photoProfileSetting(@RequestBody UserAdminDto.New newAkun){
        try{
            service.photoProfileSetting(newAkun);
            return new ResponseEntity<>(newAkun, HttpStatus.CREATED);
        } catch (DataAccessException e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/filesupload")
    public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = null;
        Map<String, Object> pesan = new HashMap<>();
        try {
            String namaFile = service.uploadFile(file);
            pesan.put("pesan", "uploaded the file successfully: " + namaFile);
            pesan.put("file", namaFile);
            return ResponseEntity.ok().body(pesan);
        } catch (Exception exception) {
            pesan.put("pesan", "cannot input file");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(pesan);
        }
    }

    @GetMapping(value = "/folder/{id}")
    public ResponseEntity<InputStreamResource>getImage(@PathVariable("id") String id){
        try{
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(
                    new InputStreamResource( service.load(id).getInputStream() ));
        }catch(IOException ex){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }


}
