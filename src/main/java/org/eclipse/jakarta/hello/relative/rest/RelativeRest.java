package org.eclipse.jakarta.hello.relative.rest;


import org.eclipse.jakarta.hello.relative.service.RelativeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/relatives")
@Produces({MediaType.APPLICATION_JSON})
public class RelativeRest {

    @Inject
    private RelativeService relativeService;

    @GET()
    @Path("/{deptId}")
    public Response getListRelativeInDepartment(@PathParam("deptId") Long deptId) {
        return Response.ok().entity(relativeService.getListRelativeInDepartment(deptId)).build();
    }

}
