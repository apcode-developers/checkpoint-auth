package com.check.point.service.auth.dao;

import com.check.point.service.auth.datatable.DataTableRequest;
import com.check.point.service.auth.dto.UserAdminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

    public Optional<UserAdminDto.New> getDataUser(String username, String password){
        String baseQuery = "select user_name as userName, user_password as userPassword, concat(first_name,' ', last_name) " +
                "as fullName, gender as gender, email as email, phone_number as phoneNumber, " +
                "birth_date as birthDate, register_time as registerTime, pin as pin, photo_profile as photoProfile from " +
                "user where user_name = :username and user_password = :password";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("username", username);
        parameterSource.addValue("password", password);

        UserAdminDto.New arsip = jdbcTemplate.queryForObject(baseQuery, parameterSource, new RowMapper<UserAdminDto.New>() {
            @Override
            public UserAdminDto.New mapRow(ResultSet resultSet, int i) throws SQLException {
                UserAdminDto.New arsip = new UserAdminDto.New();
                arsip.setUserName(resultSet.getString("userName"));
                arsip.setUserPassword(resultSet.getString("userPassword"));
                arsip.setFullName(resultSet.getString("fullName"));
                arsip.setGender(resultSet.getString("gender"));
                arsip.setEmail(resultSet.getString("email"));
                arsip.setPhoneNumber(resultSet.getString("phoneNumber"));
                arsip.setPin(resultSet.getString("pin"));
                arsip.setPhotoProfile(resultSet.getString("photoProfile"));
                arsip.setBirthDate(resultSet.getDate("birthDate"));
                arsip.setRegisterTime(resultSet.getTimestamp("registerTime"));

                return arsip;
            }
        });
        return Optional.of(arsip);
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
