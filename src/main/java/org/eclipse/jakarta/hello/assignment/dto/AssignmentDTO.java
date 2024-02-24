package org.eclipse.jakarta.hello.assignment.dto;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.jakarta.hello.employee.dto.EmployeeDTO;
import org.eclipse.jakarta.hello.project.dto.ProjectDTO;

@Getter
@Setter
public class AssignmentDTO {
    private Integer numberOfHour;
    private EmployeeDTO employee;
    private ProjectDTO project;
}
