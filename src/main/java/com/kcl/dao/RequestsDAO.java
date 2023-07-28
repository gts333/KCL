package com.kcl.dao;

import com.kcl.po.Request;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
@Mapper
public interface RequestsDAO {

    int addRequest(Request request);

    int removeRequest(int requestId);

    List<Request> selectAllRequests();

}
