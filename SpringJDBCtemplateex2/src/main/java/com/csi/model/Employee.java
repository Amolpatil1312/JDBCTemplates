package com.csi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


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

    private long empUID;

    private String empPanCard;

    @JsonFormat(pattern = "dd-MM-yyyy",timezone = "asian/kolkata")
    private Date empDOB;

    private String empGender;

    private String empEmailId;

    private String empPassword;

}
