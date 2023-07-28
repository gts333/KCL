package com.kcl.service;

import com.kcl.po.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class RequestsServiceTest {

    @Autowired
    RequestsService service;

    @Test
    void addRequest() {
        assert service.addRequest(new Request());
    }

    @Test
    void removeRequest() {
        assert service.removeRequest(1);
    }

    @Test
    void selectAllRequests() {
        System.out.println(service.selectAllRequests());
    }

    @Test
    void selectAmountOfRequestsByGroupName() {
        System.out.println(service.selectAmountOfRequestsByGroupName("group2"));
    }
}