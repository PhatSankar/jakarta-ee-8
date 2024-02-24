package org.eclipse.jakarta.hello.employee.mapper;

import org.eclipse.jakarta.hello.employee.dto.CreateEmployeeDTO;
import org.eclipse.jakarta.hello.employee.dto.EmployeeDTO;
import org.eclipse.jakarta.hello.employee.entity.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface EmployeeMapper {

    EmployeeDTO toEmployeeDTO(Employee employee);

    List<EmployeeDTO> toEmployeeDTOs(List<Employee> employee);

    Employee toEmployee(CreateEmployeeDTO createEmployeeDTO);
}
