package org.eclipse.jakarta.hello.employee.rest;

import io.swagger.annotations.*;
import org.eclipse.jakarta.hello.assignment.dto.AssignmentDTO;
import org.eclipse.jakarta.hello.employee.dto.CreateEmployeeDTO;
import org.eclipse.jakarta.hello.employee.dto.EmployeeDTO;
import org.eclipse.jakarta.hello.employee.service.EmployeeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employees")
@Api(value = "Employee API")
public class EmployeeRest {

    @Inject
    private EmployeeService employeeService;

    @POST()
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Create Employee")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Create employee successfully",
                    response = EmployeeDTO.class
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
    public Response addEmployee(@ApiParam(value = "Course to be created",
            required = true, name = "New employee's info")CreateEmployeeDTO createEmployeeDTO) throws Exception {
        return Response.ok().entity(employeeService.createEmployee(createEmployeeDTO)).build();
    }

    @GET()
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Get all employee list")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Get all employee list successfully",
                    response = EmployeeDTO.class, responseContainer = "List"
            ),
            @ApiResponse(
                    code = 500,
                    message = "Request cannot be fulfilled through browser due to server-side problems"
            )
    })
    public Response getListEmployee() {
        return Response.ok().entity(employeeService.getListEmployee()).build();
    }

    @GET()
    @Path("/not-in-project")
    @ApiOperation(value = "Get all employee list not in a project")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Get all employee list not in a project successfully",
                    response = EmployeeDTO.class, responseContainer = "List"
            ),
            @ApiResponse(
                    code = 500,
                    message = "Request cannot be fulfilled through browser due to server-side problems"
            )
    })
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getListEmployeeNotInProject() {
        return Response.ok().entity(employeeService.getListEmployeeNotInProject()).build();
    }

    @GET()
    @Path("/not-in-department")
    @ApiOperation(value = "Get all employee list not in a department")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Get all employee list not in a department successfully",
                    response = EmployeeDTO.class, responseContainer = "List"
            ),
            @ApiResponse(
                    code = 500,
                    message = "Request cannot be fulfilled through browser due to server-side problems"
            )
    })
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getListEmployeeNotInDepartment() {
        return Response.ok().entity(employeeService.getListEmployeeWorkInProjectNotInDepartment()).build();
    }
}
