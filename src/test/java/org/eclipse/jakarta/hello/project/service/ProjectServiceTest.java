package org.eclipse.jakarta.hello.project.service;

import org.eclipse.jakarta.hello.department.entity.Department;
import org.eclipse.jakarta.hello.project.dao.ProjectDAO;
import org.eclipse.jakarta.hello.project.dto.CreateProjectDTO;
import org.eclipse.jakarta.hello.project.dto.ProjectDTO;
import org.eclipse.jakarta.hello.project.entity.Area;
import org.eclipse.jakarta.hello.project.entity.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @InjectMocks
    private ProjectService projectService;

    @Mock
    private ProjectDAO projectDAO;

    private Department mockDepartment = Department.builder()
            .departmentName("VAMOS")
            .startDate(LocalDateTime.of(2024,11,28,10,12,13))
            .build();

    List<Project> mockProjects = List.of(
            Project.builder()
                    .projectName("RN")
                    .area(Area.HCM)
                    .build()
    );

    @BeforeEach
    void setUp() {
        mockDepartment.setId(1L);
        for(int i =0; i < mockProjects.size(); i++) {
            mockProjects.get(i).setId(i +1L);
            mockProjects.get(i).setManagedDepartment(mockDepartment);

        }
    }

    @Test
    void createProject_Successfully() {
        CreateProjectDTO createProjectDTO = CreateProjectDTO.builder()
                .area(Area.HCM)
                .projectName("RN")
                .depId(mockDepartment.getId())
                .build();
        when(projectDAO.add(any(Project.class))).thenReturn(mockProjects.get(0));
        ProjectDTO result = projectService.createProject(createProjectDTO);
        assertEquals(result, mockProjects.get(0));
    }

    @Test
    void createProject_failed() {

        Exception exception = new RuntimeException("Fail to create project");

        CreateProjectDTO createProjectDTO = CreateProjectDTO.builder()
                .area(Area.HCM)
                .projectName("RN")
                .depId(mockDepartment.getId())
                .build();
        when(projectDAO.add(any(Project.class))).thenThrow(exception);
       assertThrows(exception.getClass(), () -> {
           projectService.createProject(createProjectDTO);
       });
    }

    @Test
    void getListProject() {
        when(projectDAO.findAll()).thenReturn(mockProjects);
        List<ProjectDTO> result = projectService.getListProject();
        assertEquals(result, mockProjects);
    }
}