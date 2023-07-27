package com.kcl.aspects;

import com.kcl.service.AutomatedTeachingAssistantUpdateService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TeachingAssistantUpdateAspect {

    private AutomatedTeachingAssistantUpdateService automatedUpdateService;

    @Autowired
    public TeachingAssistantUpdateAspect(AutomatedTeachingAssistantUpdateService automatedUpdateService) {
        this.automatedUpdateService = automatedUpdateService;
    }

//    @Pointcut("execution(* )")_
//    public
}
