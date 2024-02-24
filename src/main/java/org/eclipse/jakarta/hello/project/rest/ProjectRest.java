package org.eclipse.jakarta.hello.project.rest;


import org.eclipse.jakarta.hello.base.exception.BadRequestException;
import org.eclipse.jakarta.hello.project.dto.CreateProjectDTO;
import org.eclipse.jakarta.hello.project.service.ProjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/projects")
public class ProjectRest {

    @Inject
    private ProjectService projectService;

    @POST()
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createProject(CreateProjectDTO createProjectDTO) throws BadRequestException {
        return Response.ok().entity(projectService.createProject(createProjectDTO)).build();
    }

    @GET()
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getListProject() {
        return Response.ok().entity(projectService.getListProject()).build();
    }
}
