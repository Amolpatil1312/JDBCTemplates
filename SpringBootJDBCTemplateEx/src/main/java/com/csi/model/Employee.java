package com.csi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    private int empId;

    private String empName;

    private String empAddress;

    private double empSalary;

    private long empContactNumber;

    private String empEmailId;

    private String empPassword;
}
