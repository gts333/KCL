package com.kcl.dto;

import com.kcl.constant.IdentityEnum;
import com.kcl.po.TeachingAssistant;
import com.kcl.po.TeachingAssistantAvailableTime;
import com.kcl.po.TeachingAssistantResourceGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeachingAssistantDTO implements Serializable {
    private String username;
    private String password = "123456";
    private String identityString;
    private boolean available;
    private boolean adjustable;
    private List<String> resourceGroupNames = new ArrayList<>();
    private List<TeachingAssistantAvailableTime> times;

    public TeachingAssistantDTO(TeachingAssistant teachingAssistant, List<TeachingAssistantResourceGroup> resourceGroupNames, List<TeachingAssistantAvailableTime> times) {
        this.username = teachingAssistant.getUsername();
        this.identityString = teachingAssistant.getIdentity().toString();
        this.available = teachingAssistant.isAvailable();
        this.adjustable = teachingAssistant.isAdjustable();
        this.resourceGroupNames = resourceGroupNames.stream().map(TeachingAssistantResourceGroup::getGroupName).collect(Collectors.toList());
        this.times = times;
    }

    public TeachingAssistant retrieveTeachingAssistant() {
        return new TeachingAssistant(username, password, IdentityEnum.valueOf(identityString), available, adjustable);
    }

}
