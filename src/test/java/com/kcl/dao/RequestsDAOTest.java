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
                .buildBasics("bob", "ll", 1)
                .buildInfo("test", "test content", AppointmentTypeEnum.DEFAULT, new Timestamp(System.currentTimeMillis()))
                .build();
        assert dao.addRequest(request) > 0;
    }

    @Test
    public void removeRequest() {
        assert dao.removeRequest(1) > 0;
    }

    @Test
    public void testSelectAllRequests() {
        System.out.println(dao.selectAllRequests());
    }



}