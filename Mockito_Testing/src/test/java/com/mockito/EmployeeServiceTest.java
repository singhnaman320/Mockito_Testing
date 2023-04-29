package com.mockito;

import com.mockito.dao.EmployeeDao;
import com.mockito.services.EmployeeService;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    private EmployeeService employeeService;

    private EmployeeDao createMockForInsertion(){

        //creating mock object using mockito

        EmployeeDao employeeDao= mock(EmployeeDao.class);


    }
}
