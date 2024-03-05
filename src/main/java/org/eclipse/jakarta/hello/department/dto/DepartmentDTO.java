package org.eclipse.jakarta.hello.department.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.eclipse.jakarta.hello.department.entity.Department;
import org.eclipse.jakarta.hello.employee.dto.EmployeeDTO;
import org.eclipse.jakarta.hello.employee.entity.Employee;
import org.eclipse.jakarta.hello.project.dto.ProjectDTO;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDTO {
    Long id;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime startDate;
    String departmentName;
    List<EmployeeDTO> employees;

    List<ProjectDTO> projects;

    public DepartmentDTO(Long id, String name) {
        setId(id);
        setDepartmentName(name);
    }
}
