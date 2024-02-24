package org.eclipse.jakarta.hello.assignment.mapper;

import org.eclipse.jakarta.hello.assignment.dto.AssignmentDTO;
import org.eclipse.jakarta.hello.assignment.dto.CreateAssignmentDTO;
import org.eclipse.jakarta.hello.assignment.entity.Assignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface AssignmentMapper {

    @Mapping(target = "employee", source = "assignment.employee")
    @Mapping(target = "project", source = "assignment.project")
    AssignmentDTO toAssignmentDTO(Assignment assignment);

    @Mapping(target = "employee", source = "assignments.employee")
    @Mapping(target = "project", source = "assignments.project")
    List<AssignmentDTO> toAssignmentDTOs(List<Assignment> assignments);

    Assignment toAssignment(CreateAssignmentDTO createAssignmentDTO);
}
