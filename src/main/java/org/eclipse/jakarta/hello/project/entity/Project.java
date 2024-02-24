package org.eclipse.jakarta.hello.project.entity;

import lombok.*;
import org.eclipse.jakarta.hello.base.entity.BaseEntity;
import org.eclipse.jakarta.hello.department.entity.Department;
import org.eclipse.jakarta.hello.employee.entity.Gender;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project  extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Area area;

    private String projectName;

    @ManyToOne
    private Department managedDepartment;

}
