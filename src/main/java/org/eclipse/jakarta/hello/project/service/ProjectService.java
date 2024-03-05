package org.eclipse.jakarta.hello.project.service;

import org.eclipse.jakarta.hello.base.exception.BadRequestException;
import org.eclipse.jakarta.hello.department.dao.DepartmentDAO;
import org.eclipse.jakarta.hello.department.entity.Department;
import org.eclipse.jakarta.hello.project.dao.ProjectDAO;
import org.eclipse.jakarta.hello.project.dto.CreateProjectDTO;
import org.eclipse.jakarta.hello.project.dto.ProjectDTO;
import org.eclipse.jakarta.hello.project.entity.Project;
import org.eclipse.jakarta.hello.project.mapper.ProjectMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Stateless
public class ProjectService {

    @Inject
    private ProjectDAO projectDAO;

    @Inject
    private ProjectMapper projectMapper;

    @Inject
    private DepartmentDAO departmentDAO;

    public ProjectDTO createProject(CreateProjectDTO createProjectDTO) throws BadRequestException {
            Optional<Department> department = departmentDAO.findById(createProjectDTO.getDepId());
            if (department.isEmpty()) {
                throw new BadRequestException("Department not found");
            }
            Project project = projectMapper.toProject(createProjectDTO);
            project.setManagedDepartment(department.get());
        return projectMapper.toProjectDTO(projectDAO.add(project));

    }

    public List<ProjectDTO> getListProject() {
        return projectMapper.toProjectDTOs(projectDAO.findAll());
    }

    public List<ProjectDTO> getListProjectAndDeptName(Long deptId) {
        List<Project> result = projectDAO.getListProjectByDeptId(deptId);
        return result.stream().map(project -> projectMapper.toProjectDTO(project)).toList();
    }

    public List<ProjectDTO> getListProjectWithTotalEmployeeAndTotalHour() {
        List<ProjectDTO> projects = projectMapper.toProjectDTOs(projectDAO.findAll());
        return projects.stream().map(projectDTO -> {
            List<Object[]> objects = projectDAO.getTotalEmployeeAndTotalHoursOfProject(projectDTO.getId());
            projectDTO.setTotalEmployee((Long) objects.get(0)[0]);
            projectDTO.setTotalWorkingHours((Long) objects.get(0)[1]);
            return projectDTO;
        }).toList();
    }

    public List<ProjectDTO> getListProjectWithTotalSalaryAndTotalHour() {
        List<ProjectDTO> projects = projectMapper.toProjectDTOs(projectDAO.findAll());
        return projects.stream().map(projectDTO -> {
            List<Object[]> objects = projectDAO.getTotalSalaryAndTotalHoursOfProject(projectDTO.getId());
            projectDTO.setTotalSalary((Long) objects.get(0)[0]);
            projectDTO.setTotalWorkingHours((Long) objects.get(0)[1]);
            return projectDTO;
        }).toList();
    }
}
