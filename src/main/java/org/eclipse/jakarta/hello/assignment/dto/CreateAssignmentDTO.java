package org.eclipse.jakarta.hello.assignment.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAssignmentDTO {
    private Integer numberOfHour;

    private Long employeeId;

    private Long projectId;
}
