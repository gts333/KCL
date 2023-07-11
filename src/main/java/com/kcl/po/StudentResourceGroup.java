package com.kcl.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResourceGroup implements Serializable {

    private int userId;
    private int groupId;

}
