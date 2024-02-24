package org.eclipse.jakarta.hello.employee.service;


import org.eclipse.jakarta.hello.department.dao.DepartmentDAO;
import org.eclipse.jakarta.hello.department.entity.Department;
import org.eclipse.jakarta.hello.employee.dao.EmployeeDAO;
import org.eclipse.jakarta.hello.employee.dto.CreateEmployeeDTO;
import org.eclipse.jakarta.hello.employee.dto.EmployeeDTO;
import org.eclipse.jakarta.hello.employee.entity.Employee;
import org.eclipse.jakarta.hello.employee.mapper.EmployeeMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Stateless
public class EmployeeService {

    @Inject
    private EmployeeDAO employeeDAO;

    @Inject
    private EmployeeMapper employeeMapper;

    @Inject
    private DepartmentDAO departmentDAO;

    public EmployeeDTO createEmployee(CreateEmployeeDTO createEmployeeDTO) throws Exception {
            Optional<Department> department = departmentDAO.findById(createEmployeeDTO.getDepId());
            if (department.isEmpty()) {
                throw new Exception("testing");
            }
            Employee employee = employeeMapper.toEmployee(createEmployeeDTO);
            employee.setDepartment(department.get());
            EmployeeDTO result = employeeMapper.toEmployeeDTO(employeeDAO.add(employee));
            return result;
    }

    public List<Employee> getListEmployee() {
        return employeeDAO.findAll();
    }

}