package org.eclipse.jakarta.hello.assignment.service;

import org.eclipse.jakarta.hello.assignment.dao.AssignmentDAO;
import org.eclipse.jakarta.hello.assignment.dto.AssignmentDTO;
import org.eclipse.jakarta.hello.assignment.dto.CreateAssignmentDTO;
import org.eclipse.jakarta.hello.assignment.entity.Assignment;
import org.eclipse.jakarta.hello.base.exception.BadRequestException;
import org.eclipse.jakarta.hello.employee.entity.Employee;
import org.eclipse.jakarta.hello.employee.entity.Gender;
import org.eclipse.jakarta.hello.project.entity.Area;
import org.eclipse.jakarta.hello.project.entity.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssignmentServiceTest {
    @InjectMocks
    private AssignmentService assignmentService;

    @Mock
    private AssignmentDAO assignmentDAO;

    private Project mockProject = Project.builder()
            .projectName("RN")
            .area(Area.HCM)
            .build();

    private Employee mockEmployee = Employee.builder()
            .firstName("Lam")
            .middleName("Thinh")
            .lastName("Phat")
            .dateOfBirth(LocalDate.of(2024,11,28))
            .salary(123456)
            .gender(Gender.MALE)
            .build();

    private Assignment mockAssignment = Assignment.builder()
            .numberOfHour(120)
            .build();

    @BeforeEach
    void setUp() {
        mockEmployee.setId(1L);
        mockProject.setId(1L);
        mockAssignment.setProject(mockProject);
        mockAssignment.setEmployee(mockEmployee);
        mockAssignment.setId(1L);
    }

    @Test
    void createAssignment_Success() throws BadRequestException {
        CreateAssignmentDTO createAssignmentDTO = CreateAssignmentDTO.builder()
                .numberOfHour(120)
                .employeeId(mockEmployee.getId())
                .projectId(mockProject.getId())
                .build();

        when(assignmentDAO.add(any(Assignment.class))).thenReturn(mockAssignment);

        AssignmentDTO result = assignmentService.createAssignment(createAssignmentDTO);

        assertEquals(result, mockAssignment);
    }

    @Test
    void createAssignment_Failed() {

        Exception exception = new RuntimeException("Fail to create assignment");
        CreateAssignmentDTO createAssignmentDTO = CreateAssignmentDTO.builder()
                .numberOfHour(120)
                .employeeId(mockEmployee.getId())
                .projectId(mockProject.getId())
                .build();

        when(assignmentDAO.add(any(Assignment.class))).thenThrow(exception);

        assertThrows(exception.getClass(), () -> {
            assignmentService.createAssignment(createAssignmentDTO);
        });
    }
}