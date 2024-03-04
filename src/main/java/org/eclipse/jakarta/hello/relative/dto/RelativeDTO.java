package org.eclipse.jakarta.hello.relative.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.jakarta.hello.employee.entity.Gender;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RelativeDTO {
    private Long id;

    private String fullName;

    private Gender gender;

    private String phoneNumber;

    private String relationship;

}
