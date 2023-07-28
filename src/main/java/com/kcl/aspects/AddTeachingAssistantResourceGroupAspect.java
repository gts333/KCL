package com.kcl.aspects;

import com.kcl.service.AutomatedRequestsAndAppointmentUpdateService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AddTeachingAssistantResourceGroupAspect {

    private AutomatedRequestsAndAppointmentUpdateService service;

    @Autowired
    public AddTeachingAssistantResourceGroupAspect(AutomatedRequestsAndAppointmentUpdateService service) {
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
