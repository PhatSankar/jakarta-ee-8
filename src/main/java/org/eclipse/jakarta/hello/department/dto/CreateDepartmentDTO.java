package org.eclipse.jakarta.hello.department.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class CreateDepartmentDTO {
    private String departmentName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
}
