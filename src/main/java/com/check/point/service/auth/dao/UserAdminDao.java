package com.check.point.service.auth.dao;

import com.check.point.service.auth.datatable.DataTableRequest;
import com.check.point.service.auth.dto.UserAdminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserAdminDao {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void deleteUser(String id) throws DataAccessException {
        String baseQuery = "DELETE from user where id = :id";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);

        jdbcTemplate.update(baseQuery, parameterSource);
    }

    public Integer getBanyak(DataTableRequest req){
        String baseQuery = "select count(id) as banyak from user ";
        if(!req.getExtraParam().isEmpty()){
            String judulAgenda = (String) req.getExtraParam().get("userName");
            baseQuery = "select count(id) as banyak from user where user_name like concat('%', ?, '%') ";
            return template.queryForObject(baseQuery, Integer.class, judulAgenda);
        } else {
            return template.queryForObject(baseQuery, Integer.class, null);
        }
    }

    public List<UserAdminDto.User> getRowUser(DataTableRequest request){
        String baseQuery = "select id as id, user_name as userName, user_password as userPassword, " +
                "concat(first_name, ' ', last_name) as fullName from user "+
                "order by "+(request.getSortCol()+1)+" "+request.getSortDir()+" limit ? offset ? ";
        if(!request.getExtraParam().isEmpty()){
            String judulProduk = (String) request.getExtraParam().get("userName");
            baseQuery = "select id as id, user_name as userName, user_password as userPassword, " +
                    "concat(first_name, ' ', last_name) as fullName from user where user_name like concat('%', ?, '%') "+
                    "order by "+(request.getSortCol()+1)+" "+request.getSortDir()+" limit ? offset ? ";
            return template.query(baseQuery, BeanPropertyRowMapper.newInstance(UserAdminDto.User.class),judulProduk,
                    request.getLength(), request.getStart());
        } else {
            return template.query(baseQuery, BeanPropertyRowMapper.newInstance(UserAdminDto.User.class),
                    request.getLength(), request.getStart());
        }

    }

}
