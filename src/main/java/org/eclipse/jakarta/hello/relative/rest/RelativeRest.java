package org.eclipse.jakarta.hello.relative.rest;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.eclipse.jakarta.hello.project.dto.ProjectDTO;
import org.eclipse.jakarta.hello.relative.dto.RelativeDTO;
import org.eclipse.jakarta.hello.relative.service.RelativeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/relatives")
@Produces({MediaType.APPLICATION_JSON})
@Api(value = "Relative API")
public class RelativeRest {

    @Inject
    private RelativeService relativeService;

    @GET()
    @Path("/{deptId}")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Get all relative in department successfully",
                    response = RelativeDTO.class, responseContainer = "List"
            ),
            @ApiResponse(
                    code = 500,
                    message = "Request cannot be fulfilled through browser due to server-side problems"
            )
    })
    public Response getListRelativeInDepartment(@PathParam("deptId") Long deptId) {
        return Response.ok().entity(relativeService.getListRelativeInDepartment(deptId)).build();
    }

}
