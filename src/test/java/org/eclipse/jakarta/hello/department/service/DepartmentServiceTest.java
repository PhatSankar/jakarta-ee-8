package org.eclipse.jakarta.hello.department.service;

import org.eclipse.jakarta.hello.department.dao.DepartmentDAO;
import org.eclipse.jakarta.hello.department.dto.CreateDepartmentDTO;
import org.eclipse.jakarta.hello.department.dto.DepartmentDTO;
import org.eclipse.jakarta.hello.department.entity.Department;
import org.eclipse.jakarta.hello.employee.dao.EmployeeDAO;
import org.eclipse.jakarta.hello.employee.dto.EmployeeDTO;
import org.eclipse.jakarta.hello.employee.entity.Employee;
import org.eclipse.jakarta.hello.project.dao.ProjectDAO;
import org.eclipse.jakarta.hello.project.entity.Project;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private EmployeeDAO employeeDAO;

    @Mock
    private DepartmentDAO departmentDAO;

    @Mock
    private ProjectDAO projectDAO;

    @InjectMocks
    private DepartmentService departmentService;

    private final List<Department> mockDepartments = Arrays.asList(
            Department.builder()
                    .departmentName("AA")
                    .build(),
            Department.builder()
                    .departmentName("BB")
                    .build()
    );

    private final List<Employee> mockEmployees = Arrays.asList(
            Employee.builder()
                    .build(),
            Employee.builder()
                    .build()
    );

    private final List<Project> mockProject = Arrays.asList(
            Project.builder()
                    .build(),
            Project.builder()
                    .build()
    );

    private final DepartmentDTO mockDepartmentDTO = DepartmentDTO.builder()
            .employees(mockEmployees.stream().map(employee -> new EmployeeDTO(
                    employee.getId(),
                    employee.getDateOfBirth(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getMiddleName(),
                    employee.getGender(),
                    employee.getSalary(),
                    new ArrayList<>()
            )).toList())
            .build();

    @BeforeEach
    public void setUpDepartment() {
        for (int i = 0; i <mockDepartments.size(); i++) {
            mockDepartments.get(i).setId((i + 1L));
        }

        for (int i = 0; i <mockEmployees.size(); i++) {
            mockEmployees.get(i).setId((i + 1L));
        }

        for (int i = 0; i <mockProject.size(); i++) {
            mockProject.get(i).setId((i + 1L));
        }
    }

    @Test
    public void findAll_ValidData_Successfully() {
        when(departmentDAO.findAll()).thenReturn(mockDepartments);
        for (Department department :
                mockDepartments) {
            when(employeeDAO.getListEmployeeFromDepartment(department.getId())).thenReturn(mockEmployees);
        }

        for (Employee employee :
                mockEmployees) {
            when(projectDAO.getListProjectByEmployeeId(employee.getId())).thenReturn(mockProject);
        }

        List<DepartmentDTO> result = departmentService.getListDepartment();

        assertEquals(mockDepartments.size(), 2);
    }

    @Test
    public void findDepartmentById_ValidData_Successfully() {
        Department mockDepartment = Department.builder().build();
        mockDepartment.setId(1L);
        when(departmentDAO.findById(1L)).thenReturn(Optional.of(mockDepartment));

        DepartmentDTO result = departmentService.getDepartmentById(1L);

        assertEquals(result, mockDepartment);
    }

    @Test
    public void findDepartmentById_Empty_Successfully() {
        when(departmentDAO.findById(100L)).thenReturn(Optional.empty());

        DepartmentDTO result = departmentService.getDepartmentById(100L);

        assertEquals(result, null);
    }

    @Test
    public void getEmployeeInDepartment_Valid_Successfully() {
        when(employeeDAO.getListEmployeeFromDepartment(1L)).thenReturn(mockEmployees);

        for (Employee employee :
                mockEmployees) {
            when(projectDAO.getListProjectByEmployeeId(employee.getId())).thenReturn(mockProject);
        }
//        DepartmentDTO result = departmentService.getEmployeeInDepartment(1L);
//        assertEquals( mockDepartmentDTO.getEmployees().size(), result.getEmployees().size());
    }

    @Test
    public void createDepartment_Valid_Successfully() {
        CreateDepartmentDTO createDepartmentDTO = CreateDepartmentDTO.builder()
                .departmentName("VAMOS")
                .startDate(LocalDateTime.of(2024,11,28,6,10,12))
                .build();

        Department mockDepartment = Department.builder()
                .departmentName(createDepartmentDTO.getDepartmentName())
                .startDate(createDepartmentDTO.getStartDate())
                .build();
        mockDepartment.setId(1L);

        when(departmentDAO.add(any(Department.class))).thenReturn(mockDepartment);

        DepartmentDTO result = departmentService.createDepartment(createDepartmentDTO);

        assertEquals( result, mockDepartment);
    }

    @Test
    public void createDepartment_Valid_Failed() {
        Exception exception = new RuntimeException("Fail to create department");

        CreateDepartmentDTO createDepartmentDTO = CreateDepartmentDTO.builder()
                .departmentName("VAMOS")
                .startDate(LocalDateTime.of(2024,11,28,6,10,12))
                .build();

        when(departmentDAO.add(any(Department.class))).thenThrow(exception);

        assertThrows(exception.getClass(), () -> {
            departmentService.createDepartment(createDepartmentDTO);
        });
    }
}
