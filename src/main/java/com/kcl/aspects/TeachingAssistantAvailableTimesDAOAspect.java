package com.kcl.aspects;

import com.kcl.service.AutomatedRequestsAndAppointmentsUpdateService;
import com.kcl.service.AutomatedTeachingAssistantsUpdateService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TeachingAssistantAvailableTimesDAOAspect {

    private AutomatedTeachingAssistantsUpdateService automatedTeachingAssistantsUpdateService;

    @Autowired
    public TeachingAssistantAvailableTimesDAOAspect(AutomatedTeachingAssistantsUpdateService automatedTeachingAssistantsUpdateService) {
        this.automatedTeachingAssistantsUpdateService = automatedTeachingAssistantsUpdateService;
    }

    @Pointcut("execution(* com.kcl.dao.TeachingAssistantAvailableTimesDAO.*(..)) " +
            "&& !execution(* com.kcl.dao.TeachingAssistantAvailableTimesDAO.selectTeachingAssistantAllTimesByTeachingAssistantUsername(..))")
    public void enhance() {

    }

    @After("enhance()")
    public void afterAdvice() {
        automatedTeachingAssistantsUpdateService.updateTeachingAssistantAvailabilityStatus();
    }
}
