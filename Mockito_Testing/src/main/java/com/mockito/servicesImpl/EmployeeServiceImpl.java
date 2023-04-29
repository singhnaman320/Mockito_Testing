package com.mockito.servicesImpl;

import com.mockito.exceptions.NoRecordFoundException;
import com.mockito.exceptions.SomeThingWrongException;
import com.mockito.modelDTO.Employee;
import com.mockito.services.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public void addEmployee(Employee employee) throws SomeThingWrongException {

    }
    @Override
    public List<Employee> allEmployeesList() throws NoRecordFoundException {
        return null;
    }
}
