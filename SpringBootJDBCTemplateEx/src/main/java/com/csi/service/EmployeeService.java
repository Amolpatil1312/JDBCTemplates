package com.csi.service;

import com.csi.model.Employee;

import java.util.List;

public interface EmployeeService {

    public void signUp(Employee employee);

    public boolean signIn(String empEmailId,String empPassword);

    public List<Employee> findAll();

    public void updateData(Employee employee,int empId);

    public void deleteById(int empId);

    public void  deleteall();
}
