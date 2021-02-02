package com.check.point.service.auth.dao;

import com.check.point.service.auth.dto.UserAdminDto;
import org.springframework.beans.factory.annotation.Autowired;
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
        String basequery = "insert into user (id, first_name, last_name , user_name, user_password)" +
                " values (:id, :firstName, :lastName , :userName, :userPassword)";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", value.getId());
        parameterSource.addValue("firstName", value.getFirstName());
        parameterSource.addValue("lastName", value.getLastName());
        parameterSource.addValue("userName", value.getUserName());
        parameterSource.addValue("userPassword", value.getUserPassword());

        return template.update(basequery, parameterSource);
    }

    public void insert(Map<String, Object> param){
        String baseQuery = "insert into group_user(id, id_user, id_role) values (?,?,?)";
        Object parameter[] = {param.get("id"),param.get("idUser"), param.get("idRole")};
        jdbcTemplate.update(baseQuery, parameter);
    }

}
