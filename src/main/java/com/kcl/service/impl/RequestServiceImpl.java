package com.kcl.service.impl;

import com.kcl.dao.RequestsDAO;
import com.kcl.po.Request;
import com.kcl.service.RequestService;
import com.kcl.service.ResourceGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;


@Service
public class RequestServiceImpl implements RequestService {

    private RequestsDAO requestsDAO;

    @Autowired
    public RequestServiceImpl(RequestsDAO requestsDAO) {
        this.requestsDAO = requestsDAO;
    }

    @Override
    public boolean addRequest(Request request) {
        return requestsDAO.addRequest(request) > 0;
    }

    @Override
    public boolean removeRequest(int requestId) {
        return requestsDAO.removeRequest(requestId) > 0;
    }

    @Override
    public List<Request> selectAllRequests() {
        return requestsDAO.selectAllRequests();
    }

}
