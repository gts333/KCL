package com.kcl.service.impl;

import com.kcl.dao.RequestsDAO;
import com.kcl.po.Request;
import com.kcl.service.RequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RequestsServiceImpl implements RequestsService {

    private RequestsDAO requestsDAO;

    @Autowired
    public RequestsServiceImpl(RequestsDAO requestsDAO) {
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

    @Override
    public int selectAmountOfRequestsByGroupName(String groupName) {
        return requestsDAO.selectAmountOfRequestsByGroupName(groupName);
    }

}
