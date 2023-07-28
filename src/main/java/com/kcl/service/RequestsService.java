package com.kcl.service;

import com.kcl.po.Request;


import java.util.List;

public interface RequestsService {

    boolean addRequest(Request request);

    boolean removeRequest(int requestId);

    List<Request> selectAllRequests();
}
