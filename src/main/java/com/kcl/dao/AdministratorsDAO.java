package com.kcl.dao;

import com.kcl.interfaces.LoginAble;
import com.kcl.po.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AdministratorsDAO extends LoginAble {

    int updateAdministrator(Administrator administrator);

    List<Administrator> selectAllAdministrators();

    Administrator selectAdministratorByUserId(int userId);

    Administrator selectAdministratorByUserName(String username);



}
