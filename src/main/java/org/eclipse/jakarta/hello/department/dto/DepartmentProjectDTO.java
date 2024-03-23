package org.eclipse.jakarta.hello.department.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.eclipse.jakarta.hello.project.dto.ProjectDTO;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentProjectDTO {
    Long id;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime startDate;
    String departmentName;

    List<ProjectDTO> projects;
}
