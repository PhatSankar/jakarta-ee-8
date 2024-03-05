package org.eclipse.jakarta.hello.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.eclipse.jakarta.hello.project.entity.Area;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectDTO {

    private Long id;

    private Area area;

    private String projectName;

    private String departmentName;

    private Long totalEmployee;

    private Long totalWorkingHours;

    private Long totalSalary;

    public ProjectDTO(Long id, String name) {
        setId(id);
        setProjectName(name);
    }
}
