package com.kcl.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceGroup {
    //primary key
    private int groupId;

    private String groupName;

    public ResourceGroup(String groupName) {
        this.groupName = groupName;
    }
}
