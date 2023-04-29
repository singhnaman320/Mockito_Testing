package com.mockito.dao;

import com.mockito.exceptions.NoRecordFoundException;
import com.mockito.exceptions.SomeThingWrongException;
import com.mockito.modelDTO.Employee;

import java.util.List;

public interface EmployeeDao {

    public void addEmployee(Employee employee) throws SomeThingWrongException;

    public List<Employee> getEmployeeList() throws NoRecordFoundException;
}
