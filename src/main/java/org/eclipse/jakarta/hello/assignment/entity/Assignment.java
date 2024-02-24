package org.eclipse.jakarta.hello.assignment.entity;

import lombok.*;
import org.eclipse.jakarta.hello.base.entity.BaseEntity;
import org.eclipse.jakarta.hello.employee.entity.Employee;
import org.eclipse.jakarta.hello.employee.entity.Gender;
import org.eclipse.jakarta.hello.project.entity.Project;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"employee_id", "project_id"})
})
public class Assignment  extends BaseEntity {

    private Integer numberOfHour;

    @ManyToOne
    @JoinColumn()
    private Employee employee;

    @ManyToOne
    @JoinColumn()
    private Project project;

}
