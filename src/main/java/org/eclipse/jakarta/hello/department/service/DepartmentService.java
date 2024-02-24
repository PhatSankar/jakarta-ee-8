package org.eclipse.jakarta.hello.department.service;

import org.eclipse.jakarta.hello.department.dao.DepartmentDAO;
import org.eclipse.jakarta.hello.department.dto.CreateDepartmentDTO;
import org.eclipse.jakarta.hello.department.dto.DepartmentDTO;
import org.eclipse.jakarta.hello.department.entity.Department;
import org.eclipse.jakarta.hello.department.mapper.DepartmentMapper;
import org.eclipse.jakarta.hello.employee.dao.EmployeeDAO;
import org.eclipse.jakarta.hello.employee.dto.EmployeeDTO;
import org.eclipse.jakarta.hello.employee.entity.Employee;
import org.eclipse.jakarta.hello.employee.mapper.EmployeeMapper;
import org.eclipse.jakarta.hello.project.dao.ProjectDAO;
import org.eclipse.jakarta.hello.project.entity.Project;
import org.eclipse.jakarta.hello.project.mapper.ProjectMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
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
        List<DepartmentDTO> result = new ArrayList<>();

        for (Department department : departments) {
            DepartmentDTO departmentDTO = departmentMapper.toDepartmentDTO(department);

            List<Employee> employees = employeeDAO.getListEmployeeFromDepartment(department.getId());
            departmentDTO.setEmployees(employees.stream().map(employee ->
                    employeeMapper.toEmployeeDTO(employee))
                    .toList()
            );
            for (EmployeeDTO employee : departmentDTO.getEmployees()) {
                List<Project> projects = projectDAO.getListProjectByEmployeeId(employee.getId());
                employee.setProjects(projectMapper.toProjectDTOs(projects));
            }
            result.add(departmentDTO);
        }

        return result;
    }

    public DepartmentDTO getDepartmentById(Long id) {
        Optional<Department> result = departmentDAO.findById(id);
        return result.isEmpty() ? null : departmentMapper.toDepartmentDTO(result.get());
    }

    public DepartmentDTO getEmployeeInDepartment(Long deptId) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        List<Employee> employeeList = employeeDAO.getListEmployeeFromDepartment(deptId);

        departmentDTO.setEmployees(employeeList.stream()
                .map(employee -> employeeMapper.toEmployeeDTO(employee))
                .collect(Collectors.toList())
        );


        for (int i = 0; i < departmentDTO.getEmployees().size(); i++) {
            List<Project> listProject = projectDAO
                    .getListProjectByEmployeeId(
                            departmentDTO.getEmployees().get(i).getId()
                    );
            departmentDTO.getEmployees().get(i).setProjects(projectMapper.toProjectDTOs(listProject));

        }
        return departmentDTO;
    }

    public DepartmentDTO createDepartment(CreateDepartmentDTO createDepartmentDTO) {
        Department department = departmentMapper.toDepartment(createDepartmentDTO);
        DepartmentDTO result = departmentMapper.toDepartmentDTO(departmentDAO.add(department));
        return result;
    }
}
