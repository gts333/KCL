package com.kcl.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * indicates which resource group a teaching assistant belongs to.
 * A teaching assistant can belong to multiple resource groups.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeachingAssistantResourceGroup implements Serializable {

    private int userId;
    private int groupId;

}
