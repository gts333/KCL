package com.kcl.aspects;

import com.kcl.service.AutomatedRequestsAndAppointmentUpdateService;
import com.kcl.service.AutomatedTeachingAssistantUpdateService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AddRequestAspect {

    private AutomatedTeachingAssistantUpdateService automatedTeachingAssistantUpdateService;
    private AutomatedRequestsAndAppointmentUpdateService automatedRequestsAndAppointmentUpdateService;

    @Autowired
    public AddRequestAspect(AutomatedTeachingAssistantUpdateService automatedTeachingAssistantUpdateService,
                            AutomatedRequestsAndAppointmentUpdateService automatedRequestsAndAppointmentUpdateService) {
        this.automatedTeachingAssistantUpdateService = automatedTeachingAssistantUpdateService;
        this.automatedRequestsAndAppointmentUpdateService = automatedRequestsAndAppointmentUpdateService;
    }

    @Pointcut("execution(* com.kcl.dao.RequestsDAO.addRequest(..))")
    public void enhance() {
    }


    @After("enhance()")
    public void afterAdvice() {
        automatedTeachingAssistantUpdateService.checkAndUpdateTeachingAssistantResourceGroup();
        automatedRequestsAndAppointmentUpdateService.checkAndUpdateRequestQueue();
    }

}
