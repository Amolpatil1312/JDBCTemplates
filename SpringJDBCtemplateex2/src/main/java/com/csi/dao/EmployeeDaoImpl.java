package com.csi.dao;

import com.csi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    String INSERT_SQL = "insert into employee (empid,empname,empaddress,empsalary,empcontactnumber,empuid,emppancard,empdob,empgender,empemailid,emppassword)values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String FINDALL_SQL = "select * from employee";

    String FIND_BY_ID = "select * from employee where empid = ?";

    String UPDATE_SQL = "update employee set empname = ?,empaddress = ?,empsalary = ?,empcontactnumber = ?,empuid = ?,emppancard = ?,empdob = ?,empgender = ?,empemailid = ?,emppassword = ? where empid = ?";

    String DELETE_BY_ID = "delete from employee where empid = ?";

    String DELETE_ALL_DATA = "Truncate table employee";

    private Employee employee(ResultSet resultSet, int numRow) throws SQLException {
        return Employee.builder().empId(resultSet.getInt(1)).empName(resultSet.getString(2)).empAddress(resultSet.getString(3)).empSalary(resultSet.getDouble(4)).empContactNumber(resultSet.getLong(5)).empUID(resultSet.getLong(6)).empPanCard(resultSet.getString(7)).empDOB(resultSet.getDate(8)).empGender(resultSet.getString(9)).empEmailId(resultSet.getString(10)).empPassword(resultSet.getString(11)).build();
    }

    @Override
    public void signUp(Employee employee) {
        jdbcTemplate.update(INSERT_SQL, employee.getEmpId(), employee.getEmpName(), employee.getEmpAddress(), employee.getEmpSalary(), employee.getEmpContactNumber(),
                employee.getEmpUID(), employee.getEmpPanCard(), employee.getEmpDOB(), employee.getEmpGender(), employee.getEmpEmailId(), employee.getEmpPassword());
    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {
        boolean flag = false;
        for(Employee employee : findAll()){
            if(employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)){
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public Employee findById(int empId) {
        return jdbcTemplate.query(FIND_BY_ID,this::employee,empId).get(0);
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(FINDALL_SQL, this::employee);
    }

    @Override
    public void updateData(Employee employee, int empId) {
        jdbcTemplate.update(UPDATE_SQL, employee.getEmpName(), employee.getEmpAddress(), employee.getEmpSalary(), employee.getEmpContactNumber(),
                employee.getEmpUID(), employee.getEmpPanCard(), employee.getEmpDOB(), employee.getEmpGender(), employee.getEmpEmailId(),
                employee.getEmpPassword(), empId);

    }

    @Override
    public void deleteById(int empId) {
        jdbcTemplate.update(DELETE_BY_ID,empId);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update(DELETE_ALL_DATA);
    }
}
