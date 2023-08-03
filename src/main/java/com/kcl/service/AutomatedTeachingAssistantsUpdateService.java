package com.kcl.service;

public interface AutomatedTeachingAssistantsUpdateService {


    /**
     * add a resource group to TAs
     * whenever the system detects a surge in needs from a resource group, allocate more TAs to that resource group
     *
     * This method is executed
     * - when "addRequest" method inside RequestsDAO is executed
     */
    void checkAndAddTeachingAssistantResourceGroup();

    /**
     * remove a resource group from TAs
     * when the system detects no appointment had been made from that resource group in the past week
     * unless it is the only resource group of a TA
     *
     * This method is executed at the end of every week, which is
     * - at the last hour of Friday
     */
    void checkAndRemoveTeachingAssistantResourceGroup();

    /**
     * On execution, scan all the TAs and their available times and update the TA's availability
     *
     * This method is executed
     * - when any method (except select) inside TeachingAssistantAvailableTimesDAO is executed
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
     * - at the Saturday of every week
     */
    void refreshTeachingAssistantAvailabilityStatus();





}
