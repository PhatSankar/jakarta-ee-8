package org.eclipse.jakarta.hello.project.entity;

import lombok.*;
import org.eclipse.jakarta.hello.assignment.entity.Assignment;
import org.eclipse.jakarta.hello.base.entity.BaseEntity;
import org.eclipse.jakarta.hello.department.entity.Department;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraphs(
        {
                @NamedEntityGraph(name = "project.assignment.entity.graph",
                        attributeNodes = {
                                @NamedAttributeNode(value = "assignments",
                                        subgraph = "assignment.employee.sub.graph"),
                                @NamedAttributeNode(value = "managedDepartment")
                        },
                        subgraphs = @NamedSubgraph(name = "assignment.employee.sub.graph",
                                attributeNodes = @NamedAttributeNode(value = "employee"))
                ),
                @NamedEntityGraph(name = "project.department.entity.graph",
                        attributeNodes = @NamedAttributeNode(value = "managedDepartment"))
        }
)
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

    @OneToMany(mappedBy = "project")
    private List<Assignment> assignments;
}
