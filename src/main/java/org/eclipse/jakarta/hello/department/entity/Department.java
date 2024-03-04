package org.eclipse.jakarta.hello.department.entity;

import lombok.*;
import org.eclipse.jakarta.hello.base.entity.BaseEntity;
import org.eclipse.jakarta.hello.department.dto.DepartmentDTO;
import org.eclipse.jakarta.hello.employee.dto.EmployeeDTO;
import org.eclipse.jakarta.hello.employee.entity.Employee;
import org.eclipse.jakarta.hello.project.dto.ProjectDTO;
import org.eclipse.jakarta.hello.project.entity.Project;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@SqlResultSetMapping(
//        name = "DepartmentWithEmployeeAndProject",
//        entities = {
//                @EntityResult(entityClass = Department.class, fields = {
//                        @FieldResult(name = "id", column = "department_id"),
//                        @FieldResult(name = "departmentName", column = "department_name"),
//                        @FieldResult(name = "startDate", column = "start_date"),
//                }),
//                @EntityResult(entityClass = Employee.class, fields = {
//                        @FieldResult(name = "id", column = "employee_id"),
//                        @FieldResult(name = "firstName", column = "first_name"),
//                        @FieldResult(name = "middleName", column = "middle_name"),
//                        @FieldResult(name = "lastName", column = "last_name"),
//                        @FieldResult(name = "dateOfBirth", column = "date_of_birth"),
//                        @FieldResult(name = "salary", column = "salary"),
//                }),
//                @EntityResult(entityClass = Project.class, fields = {
//                        @FieldResult(name = "id", column = "project_id"),
//                        @FieldResult(name = "area", column = "area"),
//                        @FieldResult(name = "projectName", column = "project_name"),
//                })
//        },
//        columns = {
//                @ColumnResult(name = "department_id"),
//                @ColumnResult(name = "employee_id"),
//                @ColumnResult(name = "project_id"),
//        }
//)
//
//@SqlResultSetMapping(
//        name = "ComplexDepartment",
//        entities = {
//                @EntityResult(entityClass = Department.class, fields = {
//                        @FieldResult(name = "id", column = "department_id"),
//                        @FieldResult(name = "departmentName", column = "department_name"),
//                        @FieldResult(name = "startDate", column = "start_date")
//                }),
//                @EntityResult(entityClass = Employee.class, fields = {
//                        @FieldResult(name = "id", column = "employee_id"),
//                        @FieldResult(name = "firstName", column = "first_name"),
//                        @FieldResult(name = "middleName", column = "middle_name"),
//                        @FieldResult(name = "lastName", column = "last_name"),
//                        @FieldResult(name = "dateOfBirth", column = "date_of_birth"),
//                        @FieldResult(name = "salary", column = "salary")
//                }),
//                @EntityResult(entityClass = Project.class, fields = {
//                        @FieldResult(name = "id", column = "project_id"),
//                        @FieldResult(name = "area", column = "area"),
//                        @FieldResult(name = "projectName", column = "project_name")
//                })
//        }
//)
//@NamedNativeQuery(
//        name = "Department.findDepartmentWithEmployeeAndProjectEmployeeWorkNativeQuery",
//        query = "SELECT d.id as department_id, d.department_name, d.start_date, " +
//                "e.id as employee_id, e.first_name, e.middle_name, e.last_name, e.date_of_birth, e.salary, " +
//                "p.id as project_id, p.area, p.project_name " +
//                "FROM department d " +
//                "LEFT JOIN employee e ON e.deptid = d.id " +
//                "LEFT JOIN assignment a ON a.employee_id = e.id " +
//                "LEFT JOIN project p on p.id = a.project_id",
//        resultSetMapping = "ComplexDepartment"
//)


//@NamedQuery(
//        name = "Department.findDepartmentWithEmployeeAndProjectEmployeeWork",
//        query = "SELECT d.id as department_id, d.departmentName, d.startDate, " +
//        "e.id as employee_id, e.firstName, e.middleName, e.lastName, e.dateOfBirth, e.salary, " +
//        "p.id as project_id, p.area, p.projectName " +
//        "FROM Department d " +
//        "LEFT JOIN Employee e ON e.department.id = d.id " +
//        "LEFT JOIN Assignment a ON a.employee.id = e.id " +
//        "LEFT JOIN Project p on p.id = a.project.id"
//)

@NamedQuery(
        name = "Department.findDepartmentWithEmployeeAndProjectEmployeeWork",
        query = "SELECT d, e, p " +
                "FROM Department d " +
                "LEFT JOIN Employee e ON e.department.id = d.id " +
                "LEFT JOIN Assignment a ON a.employee.id = e.id " +
                "LEFT JOIN Project p on p.id = a.project.id"
)
@SqlResultSetMapping(
        name = "abcmapping",
        classes = {
                @ConstructorResult(targetClass = DepartmentDTO.class, columns = {
                        @ColumnResult(name = "department_id", type = Long.class),
                        @ColumnResult(name = "department_name", type = String.class)
                }),
                @ConstructorResult(targetClass = EmployeeDTO.class, columns = {
                        @ColumnResult(name = "employee_id", type = Long.class),
                        @ColumnResult(name = "last_name", type = String.class)
                }),
                @ConstructorResult(targetClass = ProjectDTO.class, columns = {
                        @ColumnResult(name = "project_id", type = Long.class),
                        @ColumnResult(name = "project_name", type = String.class)
                })
        }
)
@NamedNativeQuery(
        name = "queryabc",
        query = "SELECT d.id as department_id, d.department_name, d.start_date, d.created_at as dept_created_at, d.updated_at as dept_updated_at, " +
                "e.id as employee_id, e.first_name, e.middle_name, e.last_name, e.date_of_birth, e.salary, e.created_at as employ_created_at, e.updated_at as employ_updated_at, " +
                "p.id as project_id, p.area, p.project_name, p.created_at as project_created_at, p.updated_at as project_updated_at " +
                "FROM department d " +
                "LEFT JOIN employee e ON e.deptid = d.id " +
                "LEFT JOIN assignment a ON a.employee_id = e.id  " +
                "LEFT JOIN project p on p.id = a.project_id",
        resultSetMapping = "abcmapping"
)
public class Department extends BaseEntity {

    private LocalDateTime startDate;
    private String departmentName;

}
