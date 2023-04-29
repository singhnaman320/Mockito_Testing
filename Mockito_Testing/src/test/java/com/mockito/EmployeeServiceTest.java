package com.mockito;

import com.mockito.dao.EmployeeDao;
import com.mockito.exceptions.NoRecordFoundException;
import com.mockito.exceptions.SomeThingWrongException;
import com.mockito.modelDTO.Employee;
import com.mockito.services.EmployeeService;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    private EmployeeService employeeService;

    private EmployeeDao createMockForInsertion(){

        //creating mock object using mockito

        EmployeeDao employeeDao= mock(EmployeeDao.class);

        try{
            // Throw NullPointerException if null is passed
            Mockito.doThrow(NullPointerException.class).when(employeeDao).addEmployee(null);

            //throw SomeThingWrongException if any instance variable has incorrect value
            ArgumentMatcher<Employee> matchArguments = employee -> employee.getEmpId() <= 0 || employee.getName() == null
                    || employee.getName().equals("") || employee.getSalary() < 1.5 || employee.getSalary() > 100.0 ||
                    employee.getDepartment() == null || employee.getDepartment().equals("");

            Mockito.doThrow(SomeThingWrongException.class).when(employeeDao).addEmployee(argThat(matchArguments));

            //throw NoRecordFoundException if no Record in the table
            Mockito.when(employeeDao.getEmployeeList()).thenThrow(NoRecordFoundException.class);

        } catch (SomeThingWrongException | NoRecordFoundException exception) {

            System.out.println(exception);
        }
        return employeeDao;
    }


}
