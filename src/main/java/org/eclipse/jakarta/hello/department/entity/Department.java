package org.eclipse.jakarta.hello.department.entity;

import lombok.*;
import org.eclipse.jakarta.hello.base.entity.BaseEntity;
import org.eclipse.jakarta.hello.employee.entity.Employee;
import org.eclipse.jakarta.hello.project.entity.Project;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedEntityGraphs({
        @NamedEntityGraph(name = "department.project.entity.graph",
                attributeNodes = @NamedAttributeNode(value = "projects")),
        @NamedEntityGraph(name = "department.employee.entity.graph",
                attributeNodes = @NamedAttributeNode(value = "employees")),
        @NamedEntityGraph(name = "department.project.employee.entity.graph",
                attributeNodes = {
                        @NamedAttributeNode(value = "projects"),
                        @NamedAttributeNode(value = "employees")}
        )
})
@NamedQuery(
        name = "Department.findDepartmentWithEmployeeAndProjectEmployeeWork",
        query = "SELECT d, e, p " +
                "FROM Department d " +
                "LEFT JOIN Employee e ON e.department.id = d.id " +
                "LEFT JOIN Assignment a ON a.employee.id = e.id " +
                "LEFT JOIN Project p on p.id = a.project.id"
)
public class Department extends BaseEntity {

    private LocalDateTime startDate;
    private String departmentName;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private Set<Employee> employees;

    @OneToMany(mappedBy = "managedDepartment", fetch = FetchType.LAZY)
    private Set<Project> projects;
}
