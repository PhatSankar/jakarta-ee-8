package org.eclipse.jakarta.hello.department.rest;

import io.swagger.annotations.*;
import org.eclipse.jakarta.hello.base.filters.Secure;
import org.eclipse.jakarta.hello.department.dto.CreateDepartmentDTO;
import org.eclipse.jakarta.hello.department.dto.DepartmentDTO;
import org.eclipse.jakarta.hello.department.entity.Department;
import org.eclipse.jakarta.hello.department.service.DepartmentService;
import org.eclipse.jakarta.hello.employee.dto.EmployeeDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/departments")
@Api(value = "Department API")
public class DepartmentRest {
    @Inject
    private DepartmentService departmentService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Secure
    @ApiOperation(value = "Get all department list")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Get all department list successfully",
                    response = DepartmentDTO.class, responseContainer = "List"
            ),
            @ApiResponse(
                    code = 500,
                    message = "Request cannot be fulfilled through browser due to server-side problems"
            )
    })
    public Response getListDepartment() {
        return Response.ok().entity(departmentService.getListDepartment()).build();
    }

//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    @Path("/testing")
//    public Response getListDepartmentNameQuery() {
//        return Response.ok().entity(departmentService.getListDepartmentByNameQuery()).build();
//    }

    @GET()
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Get employee in department")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Get employee in department successfully",
                    response = DepartmentDTO.class, responseContainer = "List"
            ),
            @ApiResponse(
                    code = 500,
                    message = "Request cannot be fulfilled through browser due to server-side problems"
            )
    })
    public Response getDepartmentById(@PathParam("id") Long id) {
        return Response.ok().entity(departmentService.getEmployeeInDepartment(id)).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Create Department")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Create Department successfully",
                    response = DepartmentDTO.class
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
    public Response createDepartment(@ApiParam(value = "Department to be created",
            required = true, name = "New Department's info")CreateDepartmentDTO createDepartmentDTO) {
        return Response.ok().entity(departmentService.createDepartment(createDepartmentDTO)).build();
    }

    @GET()
    @Path("/projects")
    @ApiOperation(value = "Get department include project")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Get department include project successfully",
                    response = DepartmentDTO.class, responseContainer = "List"
            ),
            @ApiResponse(
                    code = 500,
                    message = "Request cannot be fulfilled through browser due to server-side problems"
            )
    })
    @Produces({MediaType.APPLICATION_JSON})
    public Response getDepartmentListWithProject() {
        return Response.ok().entity(departmentService.getListDepartmentIncludeProject()).build();
    }

}
