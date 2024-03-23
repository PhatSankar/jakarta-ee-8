package org.eclipse.jakarta.hello.project.rest;


import io.swagger.annotations.*;
import org.eclipse.jakarta.hello.base.exception.BadRequestException;
import org.eclipse.jakarta.hello.base.filters.Secure;
import org.eclipse.jakarta.hello.employee.dto.EmployeeDTO;
import org.eclipse.jakarta.hello.project.dto.CreateProjectDTO;
import org.eclipse.jakarta.hello.project.dto.ProjectDTO;
import org.eclipse.jakarta.hello.project.entity.Project;
import org.eclipse.jakarta.hello.project.service.ProjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/projects")
@Api(value = "Project API")
public class ProjectRest {

    @Inject
    private ProjectService projectService;

    @POST()
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Secure
    @ApiOperation(value = "Create Project", authorizations = {@Authorization(HttpHeaders.AUTHORIZATION)})
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Create project successfully",
                    response = ProjectDTO.class
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
    public Response createProject(CreateProjectDTO createProjectDTO) throws BadRequestException {
        return Response.ok().entity(projectService.createProject(createProjectDTO)).build();
    }

    @GET()
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Get all Project list successfully",
                    response = ProjectDTO.class, responseContainer = "List"
            ),
            @ApiResponse(
                    code = 500,
                    message = "Request cannot be fulfilled through browser due to server-side problems"
            )
    })
    public Response getListProject() {
        return Response.ok().entity(projectService.getListProject()).build();
    }

    @GET()
    @Path("/departments/{deptId}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Get all project list and department name successfully",
                    response = ProjectDTO.class, responseContainer = "List"
            ),
            @ApiResponse(
                    code = 500,
                    message = "Request cannot be fulfilled through browser due to server-side problems"
            )
    })
    public Response getListProjectAndDeptName(@PathParam("deptId") Long deptId) {
        return Response.ok().entity(projectService.getListProjectAndDeptName(deptId)).build();
    }

    @GET()
    @Path("/total-employee-hour")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Get all project and tota lEmployee and hours list successfully",
                    response = ProjectDTO.class, responseContainer = "List"
            ),
            @ApiResponse(
                    code = 500,
                    message = "Request cannot be fulfilled through browser due to server-side problems"
            )
    })
    public Response getProjectTotalEmployeeTotalHour() {
        return Response.ok().entity(projectService.getListProjectWithTotalEmployeeAndTotalHour()).build();
    }

    @GET()
    @Path("/total-salary-hour")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Get all project and total salary and hours list successfully",
                    response = ProjectDTO.class, responseContainer = "List"
            ),
            @ApiResponse(
                    code = 500,
                    message = "Request cannot be fulfilled through browser due to server-side problems"
            )
    })
    public Response getProjectTotalSalaryTotalHour() {
        return Response.ok().entity(projectService.getListProjectWithTotalSalaryAndTotalHour()).build();
    }
}
