package com.kcl.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * indicates which resource group a teaching assistant belongs to.
 * A teaching assistant can belong to multiple resource groups.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeachingAssistantResourceGroup implements Serializable {

    private String username;
    private String groupName;
    private Timestamp creationTime;

    public TeachingAssistantResourceGroup(String username, String groupName) {
        this.username = username;
        this.groupName = groupName;
    }
}
