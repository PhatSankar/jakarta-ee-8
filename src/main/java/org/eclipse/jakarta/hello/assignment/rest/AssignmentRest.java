package org.eclipse.jakarta.hello.assignment.rest;

import io.swagger.annotations.*;
import org.eclipse.jakarta.hello.assignment.dto.AssignmentDTO;
import org.eclipse.jakarta.hello.assignment.dto.CreateAssignmentDTO;
import org.eclipse.jakarta.hello.assignment.service.AssignmentService;
import org.eclipse.jakarta.hello.base.exception.BadRequestException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/assignments")
@Api(value = "Assignment API")
public class AssignmentRest {
    @Inject
    private AssignmentService assignmentService;

    @POST()
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Create Assignment")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Create assignment successfully",
                    response = AssignmentDTO.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "Request sent to the server is invalid"
            ),
            @ApiResponse(
                    code = 401,
                    message = "Unauthorized to use this service"
            ),
            @ApiResponse(
                    code = 500,
                    message = "Request cannot be fulfilled through browser due to server-side problems"
            )
    })
    public Response createAssignment(@ApiParam(value = "Assignment to be created",
            required = true, name = "Assignment info") CreateAssignmentDTO createAssignmentDTO)
            throws BadRequestException {
        return Response.ok().entity(assignmentService.createAssignment(createAssignmentDTO)).build();
    }

}
