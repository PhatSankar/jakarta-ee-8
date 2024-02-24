package org.eclipse.jakarta.hello.project.service;

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

    public ProjectDTO createProject(CreateProjectDTO createProjectDTO) {
        try {
            Optional<Department> department = departmentDAO.findById(createProjectDTO.getDepId());
            if (department.isEmpty()) {

            }
            Project project = projectMapper.toProject(createProjectDTO);
            project.setManagedDepartment(department.get());
            ProjectDTO result = projectMapper.toProjectDTO(projectDAO.add(project));
            return result;
        }
        catch (Exception e) {
            throw e;
        }
    }

    public List<ProjectDTO> getListProject() {
        return projectMapper.toProjectDTOs(projectDAO.findAll());
    }
}