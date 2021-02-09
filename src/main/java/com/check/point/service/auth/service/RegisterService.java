package com.check.point.service.auth.service;

import com.check.point.service.auth.dao.RegisterDao;
import com.check.point.service.auth.dto.UserAdminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class RegisterService {

    @Autowired
    private RegisterDao dao;

    @Value("${file.directory.profile}")
    private String basePath;


    String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    SecureRandom rnd = new SecureRandom();

    public String randomString(int len){
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public int saveAdmin(UserAdminDto.New newAkun) throws SQLException {
        String randomId = UUID.randomUUID().toString();
        newAkun.setId(randomId);
        Map<String, Object> paramRegAcc = new HashMap<>();
        paramRegAcc.put("id", UUID.randomUUID().toString());
        paramRegAcc.put("idUser", randomId);
        paramRegAcc.put("idRole", 1);
        dao.insert(paramRegAcc);
        newAkun.setRegisterTime(Timestamp.valueOf(LocalDateTime.now()));
        newAkun.setAccountStatus("not verified");
        newAkun.setPin(randomString(10));
        return dao.save(newAkun);
    }

    public void photoProfileSetting(UserAdminDto.New newAkun) throws DataAccessException{
        dao.photoProfileSetting(newAkun);
    }

    public String uploadFile(MultipartFile file){
        try{
            Path root = Paths.get(basePath);
            String[] fileFrags = file.getOriginalFilename().split("\\.");
            String extension = fileFrags[fileFrags.length - 1];
            String uuid = UUID.randomUUID().toString() + "." + extension;
            Files.copy(file.getInputStream(), root.resolve(uuid));
            return uuid;
        } catch (IOException e){
            throw new RuntimeException("could not store the file. error : " + e.getMessage());
        }
    }

    public void verify(UserAdminDto.New newAkun) throws DataAccessException{
        newAkun.setAccountStatus("verified");
        dao.verify(newAkun);
    }


    public Resource load(String fileName){
        try{
            Path root = Paths.get(basePath);
            Path file = root.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return  resource;
            } else {
                throw  new RuntimeException("couldn't found the file");
            }
        } catch (MalformedURLException a){
            throw  new RuntimeException("Cannot show picture");
        }
    }

}
