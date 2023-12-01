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

    private final String INSERT_SQL = "insert into employee(empid,empname,empaddress,empsalary,empcontactnumber,empemailid,emppassword)values(?,?,?,?,?,?,?)";

    private final String SELECT_ALL = "select * from employee";

    private final String UPDATE_SQL = "update empname = ?,empaddress=?,empsalary=?,empsalary=?, empcontactnumber =?,empemailid=?,emppassword=? where empid = ?";

    private final String DELETE_BY_ID = "delete from employee where empid = ?";

    private final String FIND_BY_ID = "select * from employee where empid = ?";

    private final static  String FIND_BY_NAME = "select * from employee where empname = ?";

    private final static String delete_All = "Truncate employee";

    private Employee employee(ResultSet resultSet, int numrow) throws SQLException {
        return Employee.builder().empId(resultSet.getInt(1)).empName(resultSet.getString(2)).empAddress(resultSet.getString(3)).empSalary(resultSet.getDouble(4)).empContactNumber(resultSet.getLong(5)).empEmailId(resultSet.getString(6)).empPassword(resultSet.getString(7)).build();
    }

    @Override
    public void signUp(Employee employee) {
        jdbcTemplate.update(INSERT_SQL, employee.getEmpId(), employee.getEmpName(), employee.getEmpAddress(), employee.getEmpSalary(), employee.getEmpContactNumber(), employee.getEmpEmailId(), employee.getEmpPassword());
    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {

        boolean flag = false;

        for (Employee employee : findAll()) {
            if (employee.getEmpEmailId().equals(empEmailId) &&
                    employee.getEmpPassword().equals(empPassword)) {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(SELECT_ALL, this::employee);
    }

    @Override
    public void updateData(Employee employee, int empId) {
        jdbcTemplate.update(UPDATE_SQL, employee.getEmpName(), employee.getEmpAddress(), employee.getEmpSalary(), employee.getEmpContactNumber(), employee.getEmpEmailId(), employee.getEmpPassword(), empId);

    }

    @Override
    public void deleteById(int empId) {
        jdbcTemplate.update(DELETE_BY_ID, empId);

    }

    public void  deleteall(){
        jdbcTemplate.update(delete_All);
    }


}
