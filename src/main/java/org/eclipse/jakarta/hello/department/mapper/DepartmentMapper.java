package org.eclipse.jakarta.hello.department.mapper;

import org.eclipse.jakarta.hello.department.dto.CreateDepartmentDTO;
import org.eclipse.jakarta.hello.department.dto.DepartmentDTO;
import org.eclipse.jakarta.hello.department.entity.Department;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface DepartmentMapper {
    DepartmentDTO toDepartmentDTO(Department department);

    List<Department> toDepartmentDTOs(List<Department> department);

    Department toDepartment(CreateDepartmentDTO createDepartmentDTO);
}
