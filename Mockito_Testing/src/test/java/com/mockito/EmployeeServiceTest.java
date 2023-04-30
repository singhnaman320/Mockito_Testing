package com.mockito;

import com.mockito.dao.EmployeeDao;
import com.mockito.exceptions.NoRecordFoundException;
import com.mockito.exceptions.SomeThingWrongException;
import com.mockito.modelDTO.Employee;
import com.mockito.services.EmployeeService;
import com.mockito.servicesImpl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    EmployeeService employeeService;

    // Creating the mock object
    private EmployeeDao createMock(){

        //creating mock object using mockito

        EmployeeDao employeeDao= mock(EmployeeDao.class);

        try{
            // Throw NullPointerException if null is passed
            Mockito.doThrow(NullPointerException.class).when(employeeDao).addEmployee(null);

            //throw SomeThingWrongException if any instance variable has incorrect value
            ArgumentMatcher<Employee> matchArguments = employee -> employee.getEmpId() <= 0 || (employee.getName() == null
                    || employee.getName().equals("")) || (employee.getSalary() < 1.5 || employee.getSalary() > 100.0) ||
                    (employee.getDepartment() == null || employee.getDepartment().equals(""));

            Mockito.doThrow(SomeThingWrongException.class).when(employeeDao).addEmployee(argThat(matchArguments));

            //throw NoRecordFoundException if no Record in the table
            Mockito.when(employeeDao.getEmployeeList()).thenThrow(NoRecordFoundException.class);

        } catch (SomeThingWrongException | NoRecordFoundException exception) {

            System.out.println(exception);
        }
        return employeeDao;
    }

    @Test
    public void testAddEmployee(){

        EmployeeDao employeeDao = createMock();

        employeeService = new EmployeeServiceImpl(employeeDao);

        assertThrows(NullPointerException.class, ()->employeeService.addEmployee(null));

        assertThrows(SomeThingWrongException.class, ()->employeeService
                .addEmployee(new Employee(-1, "Naman", 2.5, "Engineering")));

        assertThrows(SomeThingWrongException.class, ()->employeeService
                .addEmployee(new Employee(3, "", 2.0, "HR")));

        assertThrows(SomeThingWrongException.class, ()->employeeService
                .addEmployee(new Employee(5, "Kaushik", 0.54, "Maintenance")));

        assertThrows(SomeThingWrongException.class, ()->employeeService
                .addEmployee(new Employee(2, "Nishant", 4.5, "")));

        try {

            verify(employeeDao, times(1)).addEmployee(null);

            verify(employeeDao, times(1))
                    .addEmployee(new Employee(-1, "Naman", 2.5, "Engineering"));

            verify(employeeDao, times(1))
                    .addEmployee(new Employee(3, "", 2.0, "HR"));

            verify(employeeDao, times(1))
                    .addEmployee(new Employee(5, "Kaushik", 0.54, "Maintenance"));

            verify(employeeDao, times(1))
                    .addEmployee(new Employee(2, "Nishant", 4.5, ""));

        } catch (SomeThingWrongException e) {

            e.printStackTrace();

        }
    }

    @Test
    public void testGetEmployeeList(){

        EmployeeDao employeeDao = createMock();
        employeeService = new EmployeeServiceImpl(employeeDao);

        try {

            assertThrows(NoRecordFoundException.class, ()->employeeService.allEmployeesList());
            verify(employeeDao, times(1)).getEmployeeList();

        } catch (NoRecordFoundException exception) {

            System.out.println(exception);;

        }
    }
}
