package com.kcl.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * indicates which resource group a teaching assistant belongs to.
 * A teaching assistant can belong to multiple resource groups.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeachingAssistantResourceGroup {

    private int userId;
    private int groupId;

}
