package org.eclipse.jakarta.hello.employee.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.eclipse.jakarta.hello.employee.entity.Gender;
import org.eclipse.jakarta.hello.project.dto.ProjectDTO;
import org.eclipse.jakarta.hello.project.entity.Project;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO {

    private Long id;
    private LocalDate dateOfBirth;
    private String firstName;
    private String lastName;
    private String middleName;

    private Gender gender;

    private Integer salary;

    public EmployeeDTO(Long id, String lastName) {
        setId(id);
        setLastName(lastName);
    }

    List<ProjectDTO> projects;
}
