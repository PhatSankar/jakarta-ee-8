package org.eclipse.jakarta.hello.department.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.eclipse.jakarta.hello.department.entity.Department;
import org.eclipse.jakarta.hello.employee.dto.EmployeeDTO;
import org.eclipse.jakarta.hello.employee.entity.Employee;

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
}
