package com.mockito.services;

import com.mockito.exceptions.NoRecordFoundException;
import com.mockito.exceptions.SomeThingWrongException;
import com.mockito.modelDTO.Employee;

import java.util.List;

public interface EmployeeService {

    public void addEmployee(Employee employee) throws SomeThingWrongException;

    public List<Employee> allEmployeesList() throws NoRecordFoundException;
}
