package com.check.point.service.auth.service;

import com.check.point.service.auth.dao.UserAdminDao;
import com.check.point.service.auth.datatable.DataTableRequest;
import com.check.point.service.auth.datatable.DataTableResponse;
import com.check.point.service.auth.dto.UserAdminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAdminService {

    @Autowired
    private UserAdminDao dao;

    public void delete(String id) throws DataAccessException {
        dao.deleteUser(id);
    }

    public Optional<UserAdminDto.New> getDataUser(String username, String password){
        return dao.getDataUser(username, password);
    }

    public DataTableResponse<UserAdminDto.User> datatables(DataTableRequest res){
        DataTableResponse<UserAdminDto.User> tables = new DataTableResponse<>();
        tables.setData(dao.getRowUser( res));
        Integer total = dao.getBanyak( res);
        tables.setRecordsTotal(total);
        tables.setRecordFiltered(total);
        tables.setDraw(res.getDraw());
        return tables;
    }
}
