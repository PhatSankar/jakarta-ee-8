package org.eclipse.jakarta.hello.employee.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.eclipse.jakarta.hello.base.entity.BaseEntity;
import org.eclipse.jakarta.hello.department.entity.Department;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(
        name = "Employee.getListNotInAnyProject",
        query = "SELECT e " +
                "FROM Employee e " +
                "WHERE e.id NOT IN (SELECT e1.id " +
                "FROM Employee e1 " +
                "JOIN Assignment a on a.employee.id = e1.id " +
                "JOIN Project p on a.project.id = p.id)"
)
public class Employee  extends BaseEntity {

    private LocalDate dateOfBirth;
    private String firstName;
    private String lastName;
    private String middleName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deptid")
    private Department department;
}
