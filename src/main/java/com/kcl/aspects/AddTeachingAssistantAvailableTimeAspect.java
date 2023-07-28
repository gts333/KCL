package com.kcl.aspects;


import com.kcl.service.AutomatedRequestsAndAppointmentUpdateService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AddTeachingAssistantAvailableTimeAspect {

    private AutomatedRequestsAndAppointmentUpdateService automatedRequestsAndAppointmentUpdateService;

    @Autowired
    public AddTeachingAssistantAvailableTimeAspect(AutomatedRequestsAndAppointmentUpdateService automatedRequestsAndAppointmentUpdateService) {
        this.automatedRequestsAndAppointmentUpdateService = automatedRequestsAndAppointmentUpdateService;
    }

    @Pointcut("execution(* com.kcl.dao.TeachingAssistantAvailableTimesDAO.addTeachingAssistantAvailableTime(..))")
    public void enhance() {

    }

    @After("enhance()")
    public void afterAdvice() {
        automatedRequestsAndAppointmentUpdateService.checkAndUpdateRequestQueue();
    }
}
