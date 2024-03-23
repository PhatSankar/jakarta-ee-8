package org.eclipse.jakarta.hello.department.mapper;

import org.eclipse.jakarta.hello.department.dto.CreateDepartmentDTO;
import org.eclipse.jakarta.hello.department.dto.DepartmentDTO;
import org.eclipse.jakarta.hello.department.dto.DepartmentProjectDTO;
import org.eclipse.jakarta.hello.department.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Mapper(componentModel = "cdi")
public interface DepartmentMapper {
    DepartmentDTO toDepartmentDTO(Department department);

    List<DepartmentDTO> toDepartmentDTOs(List<Department> department);

    DepartmentProjectDTO toDepartmentProjectDTO(Department department);

    List<DepartmentProjectDTO> toDepartmentProjectDTOs(List<Department> department);

    @Mapping(source = "startDate", target = "startDate", qualifiedByName = "localDateToLocalDateTime")
    Department toDepartment(CreateDepartmentDTO createDepartmentDTO);

    @Named("localDateToLocalDateTime")
    static LocalDateTime localDateToLocalDateTime(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MIN);
    }
}
