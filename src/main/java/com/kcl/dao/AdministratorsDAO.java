package com.kcl.dao;

import com.kcl.domain.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AdministratorsDAO {

    int updateAdministrator(Administrator administrator);

    List<Administrator> selectAllAdministrators();

    Administrator selectAdministratorByUserId(int userId);

    Administrator selectAdministratorByUserName(String username);

    int administratorLogin(String username,  String password);

}
