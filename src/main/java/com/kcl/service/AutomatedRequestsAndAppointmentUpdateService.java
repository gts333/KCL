package com.kcl.service;

public interface AutomatedRequestsAndAppointmentUpdateService {

    /**
     * remove any requests or appointment as long as they are no longer valid(had not been satisfied within this week)
     * This method is executed
     *
     * - at the beginning of every week
     */
    void removeObsoleteRequestsAndAppointments();

    /**
     * check the request queue to discover if any request is now satisfiable
     * This method is executed
     *
     * - when "addTeachingAssistantAvailableTime" inside TeachingAssistantAvailableTimeDAO is executed
     * - when "addRequest" method inside RequestsDAO is executed
     */
    void checkAndUpdateRequestQueue();
}
