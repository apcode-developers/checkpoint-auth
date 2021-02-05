package com.check.point.service.auth.dao;

import com.check.point.service.auth.dto.InfoLoginDto;
import com.check.point.service.auth.dto.UserAdminDto;
import com.check.point.service.auth.model.StatusLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Login {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate template;

    public Optional<UserAdminDto.User> getUserAdminById(String username) {
        String SQL = "select concat(first_name,' ',last_name) as full_name, user_name, user_password from user where user_name = ? ";
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL, (rs, rownum) -> {
                UserAdminDto.User kab = new UserAdminDto.User();
                kab.setUserName(rs.getString("user_name"));
                kab.setFullName(rs.getString("full_name"));
                kab.setUserPassword(rs.getString("user_password"));
                return kab;
            }, username));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<String> getRolesByUserName(String username){
        String query = "select r.role_name from group_user g join m_roles_user r on (g.id_role = r.id) " +
                " join user a on (g.id_user = a.id) where a.user_name = ?";

        Object param[] = {username};

        List<String> prop = jdbcTemplate.query(query, (rs, rownum) ->{
            return rs.getString("role_name");
        }, param);

        return prop;
    }

    public List<UserAdminDto.User> checkAccount(UserAdminDto.User value){
        String SQL = "select concat(first_name,' ',last_name) as full_name," +
                " user_name, user_password from user " +
                "where user_name = ? and user_password = ? and account_status = 'verified'";
        try {
            return jdbcTemplate.query(SQL, (rs, rownum) -> {
                UserAdminDto.User kab = new UserAdminDto.User();
                kab.setUserName(rs.getString("user_name"));
                kab.setFullName(rs.getString("full_name"));
                kab.setUserPassword(rs.getString("user_password"));
                return kab;
            }, value.getUserName(), value.getUserPassword());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public StatusLogin cekLoginValid(InfoLoginDto.User user){
        String baseQuery = "select a.user_name from login_info a inner " +
                "join user b on b.user_name = a.user_name where token = ?";
        StatusLogin slogin = new StatusLogin();
        try{
            boolean isValid = false;
            Optional<InfoLoginDto.User> hasil = Optional.of(jdbcTemplate.queryForObject(baseQuery, (rs, rownum) ->{
                InfoLoginDto.User use = new InfoLoginDto.User();
                use.setUserName(rs.getString("user_name"));
                return use;
            },user.getToken()));
            if (hasil.isPresent()){
                if(hasil.get().getUserName() != null){
                    List<String> rolesName = getRolesByUserName(hasil.get().getUserName());
                    slogin.setIsValid(true);
                    slogin.setRoles(rolesName);
                    slogin.setToken(user.getToken());
                    Map<String, Object> paramlogin = new HashMap<>();
                    paramlogin.put("as", rolesName.toArray()[0]);
                    updateInfoLogin(paramlogin,hasil.get().getUserName());
                }else{
                    slogin.setIsValid(false);
                }
            }
        }catch (Exception e){
            slogin.setIsValid(false);
            e.printStackTrace();
        }
        return slogin;
    }

    public void insertInfoLogin(Map<String, Object> param){
        String SQL = "insert into login_info (user_name, token) values (?,?)";
        Object parameter[] = {param.get("username"), param.get("token")};
        jdbcTemplate.update(SQL, parameter);
    }

    public void updateInfoLogin(Map<String, Object> param, String username){
        String SQL = "update `login_info` set `as` = ? where `user_name` = ?";
        Object parameter[] = {param.get("as"),username};
        jdbcTemplate.update(SQL, parameter);
    }

    public void logout(String token) throws DataAccessException {
        String baseQuery = "delete from login_info where token = :token";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("token", token);

        template.update(baseQuery,parameterSource);
    }

    public void clearLoginStory(String userName) throws DataAccessException {
        String baseQuery = "delete from login_info where user_name = :userName";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userName", userName);

        template.update(baseQuery,parameterSource);
    }


}

