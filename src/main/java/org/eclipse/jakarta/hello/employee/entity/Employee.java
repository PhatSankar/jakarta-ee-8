package org.eclipse.jakarta.hello.employee.entity;

import lombok.*;
import org.eclipse.jakarta.hello.assignment.entity.Assignment;
import org.eclipse.jakarta.hello.base.entity.BaseEntity;
import org.eclipse.jakarta.hello.department.entity.Department;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraphs(
        {
                @NamedEntityGraph(name = "employee.department.entity.graph",
                        attributeNodes = {
                                @NamedAttributeNode(value = "department")
                        }),
                @NamedEntityGraph(name = "employee.assignment.entity.graph",
                        attributeNodes = @NamedAttributeNode(value = "assignments",
                                subgraph = "assignment.project.sub.graph"),
                        subgraphs = @NamedSubgraph(name = "assignment.project.sub.graph",
                                attributeNodes = @NamedAttributeNode(value = "project")))
        }
)
@NamedQuery(
        name = "Employee.getListNotInAnyProject",
        query = "SELECT e " +
                "FROM Employee e " +
                "WHERE e.id NOT IN (SELECT e1.id " +
                "FROM Employee e1 " +
                "JOIN Assignment a on a.employee.id = e1.id " +
                "JOIN Project p on a.project.id = p.id)"
)
public class Employee extends BaseEntity {

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

    @OneToMany(mappedBy = "employee")
    private List<Assignment> assignments;
}
