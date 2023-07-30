package com.kcl;

import com.kcl.constant.AppointmentTypeEnum;
import com.kcl.po.Request;
import com.kcl.service.RequestsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
class GeneralTest {

    @Autowired
    RequestsService requestsService;

    @Test
    void test() {
        requestsService.addRequest(new Request(3, "alice", "group2", 2, "unSatisfiableRequest", "this request must not be fullfilled to test the functionality of request serivces", AppointmentTypeEnum.DEFAULT, new Timestamp(System.currentTimeMillis())));
    }
}
