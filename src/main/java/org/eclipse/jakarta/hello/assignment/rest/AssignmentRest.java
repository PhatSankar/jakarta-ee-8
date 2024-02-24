package org.eclipse.jakarta.hello.assignment.rest;

import org.eclipse.jakarta.hello.assignment.dto.CreateAssignmentDTO;
import org.eclipse.jakarta.hello.assignment.service.AssignmentService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/assignments")
public class AssignmentRest {
    @Inject
    private AssignmentService assignmentService;

    @POST()
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createAssignment(CreateAssignmentDTO createAssignmentDTO) {
        return Response.ok().entity(assignmentService.createAssignment(createAssignmentDTO)).build();
    }

}
