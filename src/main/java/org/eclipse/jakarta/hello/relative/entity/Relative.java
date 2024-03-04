package org.eclipse.jakarta.hello.relative.entity;

import lombok.*;
import org.eclipse.jakarta.hello.base.entity.BaseEntity;
import org.eclipse.jakarta.hello.employee.entity.Employee;
import org.eclipse.jakarta.hello.employee.entity.Gender;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(
        name = "relative-entity-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "employee", subgraph = "employee-subgraph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "employee-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("department")
                        }
                )
        }
)
@NamedQuery(
        name = "Relative.findAllByEmployeeWorkInProjectOfDepartment",
        query = "SELECT new org.eclipse.jakarta.hello.relative.dto.RelativeDTO(" +
                "r.id, r.fullName, r.gender, r.phoneNumber, r.relationship" +
                ") " +
                "FROM Relative r " +
                "LEFT JOIN Employee e ON e.id = r.employee.id " +
                "LEFT JOIN Assignment a on a.employee.id = e.id " +
                "LEFT JOIN Project p on p.id = a.project.id " +
                "WHERE p.managedDepartment.id = :deptId"
)
public class Relative extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String fullName;

    private String phoneNumber;

    private String relationship;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeid")
    private Employee employee;

}
