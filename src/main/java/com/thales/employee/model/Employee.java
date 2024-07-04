package com.thales.employee.model;

import lombok.Data;

@Data
public class Employee {
    private int id;
    private String employee_name;
    private int employee_salary;
    private int employee_age;
    private String profile_image;

    public int getAnnualSalary() {
        return this.employee_salary * 12;
    }
}
