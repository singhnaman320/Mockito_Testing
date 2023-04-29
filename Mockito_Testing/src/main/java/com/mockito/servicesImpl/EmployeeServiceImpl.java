package com.mockito.servicesImpl;

import com.mockito.dao.EmployeeDao;
import com.mockito.exceptions.NoRecordFoundException;
import com.mockito.exceptions.SomeThingWrongException;
import com.mockito.modelDTO.Employee;
import com.mockito.services.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    EmployeeDao employeeDao;
    public EmployeeServiceImpl(EmployeeDao employeeDao){
        this.employeeDao = employeeDao;

        /*

        Make employeeDao point to some implementation if it is available. In this example we don't have any implementation
        only mock implementation can be used as of now.

         */
    }
    @Override
    public void addEmployee(Employee employee) throws SomeThingWrongException {

        employeeDao.addEmployee(employee);
    }
    @Override
    public List<Employee> allEmployeesList() throws NoRecordFoundException {

        return employeeDao.getEmployeeList();
    }
}
