package com.kcl.dao;

import com.kcl.constant.AppointmentTypeEnum;
import com.kcl.po.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;


@SpringBootTest
@Transactional
class RequestsDAOTest {
    @Autowired
    RequestsDAO dao;

    @Test
    public void addRequest() {
        Request request = new Request.RequestBuilder()
                .buildBasics(1, 1, 1)
                .buildInfo("test", "test content", AppointmentTypeEnum.DEFAULT, new Timestamp(System.currentTimeMillis()))
                .build();
        assert dao.addRequest(request) > 0;
    }

    @Test
    public void removeRequest() {
        assert dao.removeRequest(1) > 0;
    }

    @Test
    public void removeObsoleteRequests() {
        assert dao.removeObsoleteRequests(new Timestamp(System.currentTimeMillis())) > 0;
    }

    @Test
    public void updateRequest() {
        Request request = new Request.RequestBuilder()
                .buildBasics(1, 1 ,3)
                .buildInfo("test", "test content", AppointmentTypeEnum.DEFAULT, new Timestamp(System.currentTimeMillis()))
                .build();
        request.setRequestId(1);
        assert dao.updateRequest(request) > 0;
    }

    @Test
    public void selectAllRequests() {
        System.out.println(dao.selectAllRequestsByGroupId(1));
    }

    @Test
    public void getRequestsSize() {
        System.out.println(dao.getRequestsSizeByGroupId(2));
    }
}