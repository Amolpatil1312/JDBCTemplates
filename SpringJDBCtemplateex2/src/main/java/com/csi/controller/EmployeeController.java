package com.csi.controller;

import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app")
public class EmployeeController {

    @Autowired
    EmployeeService employeeServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Employee employee){
        employeeServiceImpl.signUp(employee);
        return ResponseEntity.ok("SignUp dane Successfully...");
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, @PathVariable String empPassword){
        return ResponseEntity.ok(employeeServiceImpl.signIn(empEmailId, empPassword));
    }

    @GetMapping("/findById/{empId}")
    public ResponseEntity<Employee> findById(@PathVariable int empId){
        return ResponseEntity.ok(employeeServiceImpl.findById(empId));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Employee>> findAll(){
        return ResponseEntity.ok(employeeServiceImpl.findAll());
    }

    @PutMapping("/updatebyid/{empId}")
    public ResponseEntity<String> updateById(@RequestBody Employee employee,@PathVariable int empId){
        employeeServiceImpl.updateData(employee, empId);
        return ResponseEntity.ok("Employee Update successfully");
    }

    @DeleteMapping("/deletebyid/{empId}")
    public ResponseEntity<String> deleteById(@PathVariable int empId){
        employeeServiceImpl.deleteById(empId);
        return ResponseEntity.ok("Data Deleted Successfully..");
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll(){
        employeeServiceImpl.deleteAll();
        return ResponseEntity.ok("All data Deleted Successfully");
    }

    @GetMapping("/findbyname/{empName}")
    public ResponseEntity<List<Employee>> findByName(@PathVariable String empName){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().filter(employee -> employee.getEmpName().equals(empName)).toList());
    }

    @GetMapping("/findbyemail/{empEmaiId}")
    public ResponseEntity<Employee> findByEmail(@PathVariable String empEmaiId){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().filter(emp->emp.getEmpEmailId().equals(empEmaiId)).toList().get(0));
    }

    @GetMapping("/findbycontactnumber/{empContact}")
    public ResponseEntity<Employee> findByConatactNumber(@PathVariable long empContact){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().filter(emp->emp.getEmpContactNumber()==empContact).toList().get(0));
    }

    @GetMapping("/findbyanyinput/{anyinput}")
    public ResponseEntity<List<Employee>> findByAnyInput(@PathVariable String anyinput){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().filter(emp->
                emp.getEmpName().equals(anyinput) || emp.getEmpAddress().equals(anyinput)
        || emp.getEmpGender().equals(anyinput) || emp.getEmpEmailId().equals(anyinput)
        || emp.getEmpId()==Integer.parseInt(anyinput) || emp.getEmpContactNumber()==Integer.parseInt(anyinput)).toList());
    }

    @GetMapping("/sortbyid")
    public ResponseEntity<List<Employee>> sortById(){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().sorted(Comparator.comparing(Employee::getEmpId)).toList());
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<List<Employee>> sortByName(){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().sorted(Comparator.comparing(Employee::getEmpName)).toList());
    }

    @GetMapping("/sortbysalary")
    public ResponseEntity<List<Employee>> sortBySalary(){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().sorted(Comparator.comparingDouble(Employee::getEmpSalary)).toList());
    }

    @GetMapping("/filterbysalary/{empsalary}")
    public ResponseEntity<List<Employee>> filterBySalary(@PathVariable double empsalary){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().filter(emp->emp.getEmpSalary()>=empsalary).toList());
    }

    @GetMapping("/filterbysalarymaxmin/{minsalary}/{maxsalary}")
    public ResponseEntity<List<Employee>> filterBySalaryminmax(@PathVariable double minsalary,@PathVariable double maxsalary){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().filter(emp->emp.getEmpSalary()>=minsalary && emp.getEmpSalary()<=minsalary).toList());
    }

    @GetMapping("/loaneligibility/{empId}")
    public ResponseEntity<String> loanEligibility(@PathVariable int empId){
        String msg = "";
        if(employeeServiceImpl.findById(empId) !=null && employeeServiceImpl.findById(empId).getEmpSalary()>=80000){
            msg = "Employee is Eligible for the Loan";
        }else {
            msg = "Employee is Not Eligible To Loan";
        }
        return ResponseEntity.ok(msg);
    }

    @GetMapping("/findbydob/{empDOB}")
    public ResponseEntity<List<Employee>> findByDOB(@PathVariable String empDOB){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().filter(emp->emp.getEmpDOB().equals(dateFormat.format(empDOB))).collect(Collectors.toList()));
    }




}
