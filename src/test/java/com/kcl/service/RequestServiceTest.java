package com.kcl.service;

import com.kcl.po.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class RequestServiceTest {

    @Autowired
    RequestService service;

    @Test
    void addRequest() {
        assert service.addRequest(new Request());
    }

    @Test
    void removeRequest() {
        assert service.removeRequest(1);
    }

    @Test
    void removeObsoleteRequests() {
        assert service.removeObsoleteRequests(new Timestamp(System.currentTimeMillis()));
    }

    @Test
    void selectAllRequests() {
        System.out.println(service.selectAllRequests());
    }
}