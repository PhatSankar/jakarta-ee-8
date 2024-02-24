package org.eclipse.jakarta.hello.employee.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.eclipse.jakarta.hello.employee.entity.Gender;

import javax.json.bind.annotation.JsonbDateFormat;
import java.time.LocalDate;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeDTO {

    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private String firstName;

    private String middleName;

    private String lastName;

    private Gender gender;

    private Integer salary;

    private Long depId;
}
