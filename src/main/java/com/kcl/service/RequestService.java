package com.kcl.service;

import com.kcl.po.Request;

import java.sql.Timestamp;
import java.util.List;

public interface RequestService {

    boolean addRequest(Request request);

    boolean removeRequest(int requestId);

    List<Request> selectAllRequests();
}
