package com.csi.controller;

import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import com.csi.service.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Employee employee){
        log.info("#############TRYING TO SIGNUP THE EMPLOYEE#################");
        employeeService.signUp(employee);
        return ResponseEntity.ok("SignUp Done Successfully...");
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId,@PathVariable String empPassword){
        log.info("################TRYING TO SIGNIN FOR EMPLOYEE##############");
        return ResponseEntity.ok(employeeService.signIn(empEmailId, empPassword));
    }

    @GetMapping("/findall")
    public  ResponseEntity<List<Employee>> findAll(){
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<String> updateData(@PathVariable int empId,@RequestBody Employee employee){
        employeeService.updateData(employee, empId);
        return ResponseEntity.ok("Data Updated Successfully...");
    }

    @DeleteMapping("/deletebyid/{empId}")
    public ResponseEntity<String> deleteById(@PathVariable int empId){
        employeeService.deleteById(empId);
        return ResponseEntity.ok("Data Deleted Successfully....");
    }

    @GetMapping("/findbyid/{empId}")
    public ResponseEntity<List<Employee>> findById(@PathVariable int empId){
        return ResponseEntity.ok(employeeService.findAll().stream().filter(emp->emp.getEmpId()==empId).collect(Collectors.toList()));
    }

    @GetMapping("/findbyname/{empName}")
    public ResponseEntity<List<Employee>> findByName(@PathVariable String empName){
        return ResponseEntity.ok(employeeService.findAll().stream().filter(emp->emp.getEmpName().equals(empName)).collect(Collectors.toList()));
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<List<Employee>> sortByName(){
        return ResponseEntity.ok(employeeService.findAll().stream().sorted(Comparator.comparing(Employee::getEmpName)).toList());
    }

    @GetMapping("/sortbysalary")
    public ResponseEntity<List<Employee>> sortBySalary(){
        return ResponseEntity.ok(employeeService.findAll().stream().sorted(Comparator.comparing(Employee::getEmpSalary)).toList());
    }

    @DeleteMapping("deleteall")
    public ResponseEntity<String> deleteAll(){
        employeeService.deleteall();
        return ResponseEntity.ok("All Data Deleted Successfully");

    }





}
