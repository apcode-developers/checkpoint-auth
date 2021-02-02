package com.check.point.service.auth.service;

import com.check.point.service.auth.dao.RegisterDao;
import com.check.point.service.auth.dto.UserAdminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class RegisterService {

    @Autowired
    private RegisterDao dao;

    public int saveAdmin(UserAdminDto.New newAkun) throws SQLException {
        String randomId = UUID.randomUUID().toString();
        newAkun.setId(randomId);
        Map<String, Object> paramRegAcc = new HashMap<>();
        paramRegAcc.put("id", UUID.randomUUID().toString());
        paramRegAcc.put("idUser", newAkun.getId());
        paramRegAcc.put("idRole", 2);
        dao.insert(paramRegAcc);
        return dao.save(newAkun);
    }
}
