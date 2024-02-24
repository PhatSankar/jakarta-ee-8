package org.eclipse.jakarta.hello.assignment.service;

import org.eclipse.jakarta.hello.assignment.dao.AssignmentDAO;
import org.eclipse.jakarta.hello.assignment.dto.AssignmentDTO;
import org.eclipse.jakarta.hello.assignment.dto.CreateAssignmentDTO;
import org.eclipse.jakarta.hello.assignment.entity.Assignment;
import org.eclipse.jakarta.hello.assignment.mapper.AssignmentMapper;
import org.eclipse.jakarta.hello.base.exception.BadRequestException;
import org.eclipse.jakarta.hello.employee.dao.EmployeeDAO;
import org.eclipse.jakarta.hello.employee.entity.Employee;
import org.eclipse.jakarta.hello.project.dao.ProjectDAO;
import org.eclipse.jakarta.hello.project.entity.Project;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Optional;

@Stateless
public class AssignmentService {

    @Inject
    private AssignmentDAO assignmentDAO;

    @Inject
    private AssignmentMapper assignmentMapper;

    @Inject
    private EmployeeDAO employeeDAO;

    @Inject
    private ProjectDAO projectDAO;

    public AssignmentDTO createAssignment(CreateAssignmentDTO createAssignmentDTO) throws BadRequestException {
            Optional<Employee> employee = employeeDAO.findById(createAssignmentDTO.getEmployeeId());
            if (employee.isEmpty()) {
                throw new BadRequestException("Employee not found");
            }

            Optional<Project> project = projectDAO.findById(createAssignmentDTO.getProjectId());
            if (project.isEmpty()) {
                throw new BadRequestException("Project not found");
            }

            Assignment assignment = assignmentMapper.toAssignment(createAssignmentDTO);
            assignment.setEmployee(employee.get());
            assignment.setProject(project.get());

            AssignmentDTO result = assignmentMapper.toAssignmentDTO(assignmentDAO.add(assignment));

            return result;
    }
}
