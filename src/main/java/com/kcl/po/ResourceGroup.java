package com.kcl.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceGroup implements Serializable {
    //primary key
    private int groupId;

    private String groupName;

    public ResourceGroup(String groupName) {
        this.groupName = groupName;
    }
}
