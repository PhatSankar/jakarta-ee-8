package org.eclipse.jakarta.hello.project.entity;

import lombok.*;
import org.eclipse.jakarta.hello.base.entity.BaseEntity;
import org.eclipse.jakarta.hello.department.entity.Department;
import org.eclipse.jakarta.hello.employee.entity.Gender;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(
        name = "Project.getTotalEmployeeAndTotalNumberOfHours",
        query = "SELECT COUNT(e.id), SUM(a.numberOfHour)" +
                "FROM Project p " +
                "LEFT JOIN Assignment a ON a.project.id = p.id " +
                "LEFT JOIN Employee e ON a.employee.id = e.id " +
                "WHERE p.id = :projectId " +
                "GROUP BY p.id"
)
@NamedQuery(
        name = "Project.getTotalSalaryAndTotalNumberOfHours",
        query = "SELECT SUM(e.salary), SUM(a.numberOfHour)" +
                "FROM Project p " +
                "LEFT JOIN Assignment a ON a.project.id = p.id " +
                "LEFT JOIN Employee e ON a.employee.id = e.id " +
                "WHERE p.id = :projectId " +
                "GROUP BY p.id"
)
public class Project extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Area area;

    private String projectName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department managedDepartment;

}
