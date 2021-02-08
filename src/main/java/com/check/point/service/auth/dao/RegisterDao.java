package com.check.point.service.auth.dao;

import com.check.point.service.auth.dto.UserAdminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Map;

@Component
public class RegisterDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate template;

    public int save(UserAdminDto.New value) throws SQLException {
        String basequery = "insert into user (id, first_name, last_name , user_name, user_password, gender," +
                "email, phone_number, birth_date, register_time, account_status)" +
                " values (:id, :firstName, :lastName , :userName, :userPassword, " +
                ":gender, :email, :phoneNumber, :birthDate, :registerTime, :accountStatus)";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", value.getId());
        parameterSource.addValue("firstName", value.getFirstName());
        parameterSource.addValue("lastName", value.getLastName());
        parameterSource.addValue("userName", value.getUserName());
        parameterSource.addValue("userPassword", value.getUserPassword());
        parameterSource.addValue("gender", value.getGender());
        parameterSource.addValue("email", value.getEmail());
        parameterSource.addValue("phoneNumber", value.getPhoneNumber());
        parameterSource.addValue("birthDate", value.getBirthDate());
        parameterSource.addValue("registerTime", value.getRegisterTime());
        parameterSource.addValue("accountStatus", value.getAccountStatus());

        return template.update(basequery, parameterSource);
    }

    public void verify(UserAdminDto.New value) throws DataAccessException{
        String baseQuery="update user set account_status = :accountStatus where user_name = :userName";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("accountStatus", value.getAccountStatus());
        parameterSource.addValue("userName", value.getUserName());

        template.update(baseQuery, parameterSource);
    }

    public void insert(Map<String, Object> param){
        String baseQuery = "insert into group_user(id, id_user, id_role) values (?,?,?)";
        Object parameter[] = {param.get("id"),param.get("idUser"), param.get("idRole")};
        jdbcTemplate.update(baseQuery, parameter);
    }

}
