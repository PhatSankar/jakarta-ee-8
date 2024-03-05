package org.eclipse.jakarta.hello.employee.service;

import org.eclipse.jakarta.hello.department.entity.Department;
import org.eclipse.jakarta.hello.employee.dao.EmployeeDAO;
import org.eclipse.jakarta.hello.employee.dto.CreateEmployeeDTO;
import org.eclipse.jakarta.hello.employee.dto.EmployeeDTO;
import org.eclipse.jakarta.hello.employee.entity.Employee;
import org.eclipse.jakarta.hello.employee.entity.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeDAO employeeDAO;

    @InjectMocks
    private EmployeeService employeeService;

    private Department mockDepartment = Department.builder()
            .departmentName("VAMOS")
            .startDate(LocalDateTime.of(2024,11,28,10,12,13))
            .build();

    private List<Employee> mockEmployess = List.of(
            Employee.builder()
                    .firstName("Lam")
                    .middleName("Thinh")
                    .lastName("Phat")
                    .dateOfBirth(LocalDate.of(2024,11,28))
                    .salary(123456)
                    .gender(Gender.MALE)
                    .build()
    );

    @BeforeEach
    public void init() {
        mockDepartment.setId(1L);
        for(int i =0; i < mockEmployess.size(); i++) {
            mockEmployess.get(i).setId(i +1L);
            mockEmployess.get(i).setDepartment(mockDepartment);

        }
    }

    @Test
    void createEmployee_Success() throws Exception {
        CreateEmployeeDTO createEmployeeDTO = new CreateEmployeeDTO(
                LocalDate.of(2024,11,28),
                "Lam","Thinh","Phat", Gender.MALE,123456, 1L
        );
        when(employeeDAO.add(any(Employee.class))).thenReturn(mockEmployess.get(0));
        EmployeeDTO result = employeeService.createEmployee(createEmployeeDTO);
        assertEquals(result, mockEmployess.get(0));
    }

    @Test
    void createEmployee_Failed() {
        Exception exception = new RuntimeException("Fail to create employee");
        CreateEmployeeDTO createEmployeeDTO = new CreateEmployeeDTO(
                LocalDate.of(2024,11,28),
                "Lam","Thinh","Phat", Gender.MALE,123456, 1L
        );
        when(employeeDAO.add(any(Employee.class))).thenThrow(exception);
        assertThrows(exception.getClass(), () -> {
            employeeService.createEmployee(createEmployeeDTO);
        });
    }

    @Test
    void getListEmployee() {
        when(employeeDAO.findAll()).thenReturn(mockEmployess);

        List<EmployeeDTO> result = employeeService.getListEmployee();

        assertEquals(result, mockEmployess);
    }
}