package org.eclipse.jakarta.hello.project.mapper;

import org.eclipse.jakarta.hello.project.dto.CreateProjectDTO;
import org.eclipse.jakarta.hello.project.dto.ProjectDTO;
import org.eclipse.jakarta.hello.project.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface ProjectMapper {

    @Mapping(target = "departmentName", source = "managedDepartment.departmentName")
    ProjectDTO toProjectDTO(Project project);

//    @Mapping(target = "departmentName", source = "managedDepartment.departmentName")
    List<ProjectDTO> toProjectDTOs(List<Project> project);

    Project toProject(CreateProjectDTO createProjectDTO);

}
