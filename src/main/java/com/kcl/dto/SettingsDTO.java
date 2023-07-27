package com.kcl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SettingsDTO {
    private String name;
    private String value;
}
