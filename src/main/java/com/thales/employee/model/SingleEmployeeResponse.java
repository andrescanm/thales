package com.thales.employee.model;

import lombok.Data;

@Data
public class SingleEmployeeResponse {
    private String status;
    private Employee data;
    private String message;
}
