package com.kcl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class VerificationResultDTO implements Serializable {

    String message;
    boolean success;

}
