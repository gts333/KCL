package com.kcl.aspects;

import com.kcl.service.AutomatedRequestsAndAppointmentsUpdateService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AddTeachingAssistantResourceGroupAspect {

    private AutomatedRequestsAndAppointmentsUpdateService service;

    @Autowired
    public AddTeachingAssistantResourceGroupAspect(AutomatedRequestsAndAppointmentsUpdateService service) {
        this.service = service;
    }

    @Pointcut("execution(* com.kcl.dao.TeachingAssistantResourceGroupsDAO.addTeachingAssistantResourceGroup(..))")
    public void enhance() {

    }

    @After("enhance()")
    public void afterAdvice() {
        service.checkAndUpdateRequestQueue();
    }
}
