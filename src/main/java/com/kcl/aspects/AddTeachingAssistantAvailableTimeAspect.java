package com.kcl.aspects;


import com.kcl.service.AutomatedRequestsAndAppointmentsUpdateService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AddTeachingAssistantAvailableTimeAspect {

    private AutomatedRequestsAndAppointmentsUpdateService automatedRequestsAndAppointmentsUpdateService;

    @Autowired
    public AddTeachingAssistantAvailableTimeAspect(AutomatedRequestsAndAppointmentsUpdateService automatedRequestsAndAppointmentsUpdateService) {
        this.automatedRequestsAndAppointmentsUpdateService = automatedRequestsAndAppointmentsUpdateService;
    }

    @Pointcut("execution(* com.kcl.dao.TeachingAssistantAvailableTimesDAO.addTeachingAssistantAvailableTime(..))")
    public void enhance() {

    }

    @After("enhance()")
    public void afterAdvice() {
        automatedRequestsAndAppointmentsUpdateService.checkAndUpdateRequestQueue();
    }
}
