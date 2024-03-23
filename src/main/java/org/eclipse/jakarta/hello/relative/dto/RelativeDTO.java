package org.eclipse.jakarta.hello.relative.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.jakarta.hello.employee.entity.Gender;
import org.eclipse.jakarta.hello.relative.entity.Relative;

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

    public RelativeDTO(Relative r) {
        this.id = r.getId();
        this.fullName = r.getFullName();
        this.gender = r.getGender();
        this.phoneNumber = r.getPhoneNumber();
        this.relationship = r.getRelationship();
    }

}
