package com.kcl.service;

public interface AutomatedTeachingAssistantUpdateService {


    /**
     * update the TA's resource groups
     * 1. whenever the system detects a surge in needs from a resource group, allocate more TA to the resource group
     * 2. when the system detects a TA had not been booked by any student from that resource group in the past week,
     * remove that TA from that resource group unless it is the only group left.
     *
     * This method is executed
     * - when "addRequest" method inside RequestsDAO is executed
     */
    void checkAndUpdateTeachingAssistantResourceGroup();

    /**
     * On execution, scan all the TAs and their available times and update the TA's availability
     *
     * This method is executed
     * - when any method inside TeachingAssistantAvailableTimeDAO is executed
     */
    void updateTeachingAssistantAvailabilityStatus();

    /**
     * mark any TA's time that had gone past current weekday as unavailable
     * This method is executed
     *
     * - at the beginning of every day
     */
    void updateUnreachableTeachingAssistantAvailableTimes();

    /**
     * mark all teaching assistants and their times as available at the beginning of every week
     * This method is executed
     *
     * - at the beginning of every week
     */
    void refreshTeachingAssistantAvailabilityStatus();





}
