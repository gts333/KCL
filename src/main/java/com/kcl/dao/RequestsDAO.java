package com.kcl.dao;

import com.kcl.domain.Request;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
@Mapper
public interface RequestsDAO {

    int addRequest(Request request);

    int removeRequest(int requestId);

    int removeObsoleteRequests(Timestamp timestamp);

    int updateRequest(Request request);

    List<Request> selectAllRequestsByGroupId(int groupId);

    int getRequestsSizeByGroupId(int groupId);

}
