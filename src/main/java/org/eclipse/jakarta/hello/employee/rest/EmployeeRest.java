package org.eclipse.jakarta.hello.employee.rest;

import org.eclipse.jakarta.hello.employee.dto.CreateEmployeeDTO;
import org.eclipse.jakarta.hello.employee.service.EmployeeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employees")
public class EmployeeRest {

    @Inject
    private EmployeeService employeeService;

    @POST()
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addEmployee(CreateEmployeeDTO createEmployeeDTO) throws Exception {
        return Response.ok().entity(employeeService.createEmployee(createEmployeeDTO)).build();
    }

    @GET()
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getListEmployee() {
        return Response.ok().entity(employeeService.getListEmployee()).build();
    }
}
