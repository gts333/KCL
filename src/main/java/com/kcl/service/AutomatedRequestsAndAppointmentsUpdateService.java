package com.kcl.service;

public interface AutomatedRequestsAndAppointmentsUpdateService {

    /**
     * remove any requests or appointment as long as they are no longer valid(had not been satisfied within this week)
     * This method is executed
     *
     *  at the Saturday of every week
     */
    void removeObsoleteRequestsAndAppointments();

    /**
     * check the request queue to discover if any request is now satisfiable
     * This method is executed
     *
     * - when "addTeachingAssistantAvailableTime" inside TeachingAssistantAvailableTimesDAO is executed
     * - when "addTeachingAssistantResourceGroup" inside TeachingAssistantResourceGroupsDAO is executed
     * - when "addRequest" method inside RequestsDAO is executed
     */
    void checkAndUpdateRequestQueue();
}
