package com.kcl.aspects;

import com.kcl.service.AutomatedTeachingAssistantUpdateService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TeachingAssistantAvailableTimesDAOAspect {

    private AutomatedTeachingAssistantUpdateService automatedTeachingAssistantUpdateService;

    @Autowired
    public TeachingAssistantAvailableTimesDAOAspect(AutomatedTeachingAssistantUpdateService automatedTeachingAssistantUpdateService) {
        this.automatedTeachingAssistantUpdateService = automatedTeachingAssistantUpdateService;
    }

    @Pointcut("execution(* com.kcl.dao.TeachingAssistantAvailableTimesDAO.*(..)) " +
            "&& !execution(* com.kcl.dao.TeachingAssistantAvailableTimesDAO.selectTeachingAssistantAllTimesByTeachingAssistantUsername(..))")
    public void enhance() {

    }

    @After("enhance()")
    public void afterAdvice() {
        automatedTeachingAssistantUpdateService.updateTeachingAssistantAvailabilityStatus();
    }
}
