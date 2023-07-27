package com.kcl.dto;

import com.kcl.constant.IdentityEnum;
import com.kcl.constant.PriorityStatusEnum;
import com.kcl.po.Student;
import com.kcl.po.StudentResourceGroup;
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
public class StudentDTO implements Serializable {
    private String username;
    private String password = "123456";
    private String identityString;
    private String priorityStatusString;
    private List<String> resourceGroupNames = new ArrayList<>();

    public StudentDTO(Student student, List<StudentResourceGroup> resourceGroups) {
        this.username = student.getUsername();
        this.identityString = student.getIdentity().toString();
        this.priorityStatusString = student.getPriorityStatus().toString();
        this.resourceGroupNames = resourceGroups.stream().map(StudentResourceGroup::getGroupName).collect(Collectors.toList());
    }

    public Student retrieveStudent() {
        return new Student(username, password, IdentityEnum.valueOf(identityString), PriorityStatusEnum.valueOf(priorityStatusString));
    }
}
