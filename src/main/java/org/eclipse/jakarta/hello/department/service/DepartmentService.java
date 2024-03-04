package org.eclipse.jakarta.hello.department.service;

import org.eclipse.jakarta.hello.department.dao.DepartmentDAO;
import org.eclipse.jakarta.hello.department.dto.CreateDepartmentDTO;
import org.eclipse.jakarta.hello.department.dto.DepartmentDTO;
import org.eclipse.jakarta.hello.department.entity.Department;
import org.eclipse.jakarta.hello.department.mapper.DepartmentMapper;
import org.eclipse.jakarta.hello.employee.dao.EmployeeDAO;
import org.eclipse.jakarta.hello.employee.entity.Employee;
import org.eclipse.jakarta.hello.employee.mapper.EmployeeMapper;
import org.eclipse.jakarta.hello.project.dao.ProjectDAO;
import org.eclipse.jakarta.hello.project.dto.ProjectDTO;
import org.eclipse.jakarta.hello.project.mapper.ProjectMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class DepartmentService {
    @Inject
    private DepartmentDAO departmentDAO;

    @Inject
    private EmployeeDAO employeeDAO;

    @Inject
    private ProjectDAO projectDAO;

    @Inject
    private DepartmentMapper departmentMapper;

    @Inject
    private EmployeeMapper employeeMapper;

    @Inject
    private ProjectMapper projectMapper;

    public List<DepartmentDTO> getListDepartment() {
        List<Department> departments = departmentDAO.findAll();

        return departments.stream()
                .map(department -> departmentMapper.toDepartmentDTO(department))
                .map(departmentDTO -> {
                    List<Employee> employees = employeeDAO.getListEmployeeFromDepartment(departmentDTO.getId());
                    departmentDTO.setEmployees(employees.stream()
                            .map(employee -> employeeMapper.toEmployeeDTO(employee)).toList());
                    return departmentDTO;
                })
                .map(departmentDTO -> {
                    departmentDTO.getEmployees().stream()
                            .forEach(employeeDTO ->
                            {
                                employeeDTO.setProjects(projectMapper
                                        .toProjectDTOs(projectDAO
                                                .getListProjectByEmployeeId(employeeDTO.getId())));
                            });
                    return departmentDTO;
                })
                .toList();
    }

    public List getListDepartmentByNameQuery() {
        return departmentDAO.getDepartmentWithEmployeeAndName();
    }

    public DepartmentDTO getDepartmentById(Long id) {
        Optional<Department> result = departmentDAO.findById(id);
        return result.isEmpty() ? null : departmentMapper.toDepartmentDTO(result.get());
    }

    public DepartmentDTO getEmployeeInDepartment(Long deptId) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        List<Employee> employeeList = employeeDAO.getListEmployeeFromDepartment(deptId);

        departmentDTO.setEmployees(employeeMapper.toEmployeeDTOs(employeeList));

        departmentDTO.getEmployees().stream().forEach(employeeDTO -> {
            List<ProjectDTO> projectDTOS = projectMapper.toProjectDTOs(projectDAO.getListProjectByEmployeeId(
                    employeeDTO.getId()
            ));
            employeeDTO.setProjects(projectDTOS);
        });
        return departmentDTO;
    }

    public DepartmentDTO createDepartment(CreateDepartmentDTO createDepartmentDTO) {
        Department department = departmentMapper.toDepartment(createDepartmentDTO);
        return departmentMapper.toDepartmentDTO(departmentDAO.add(department));
    }
}
