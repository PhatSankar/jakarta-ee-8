package org.eclipse.jakarta.hello.department.rest;

import org.eclipse.jakarta.hello.department.dto.CreateDepartmentDTO;
import org.eclipse.jakarta.hello.department.entity.Department;
import org.eclipse.jakarta.hello.department.service.DepartmentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/departments")
public class DepartmentRest {
    @Inject
    private DepartmentService departmentService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getListDepartment() {
        return Response.ok().entity(departmentService.getListDepartment()).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/testing")
    public Response getListDepartmentNameQuery() {
        return Response.ok().entity(departmentService.getListDepartmentByNameQuery()).build();
    }

    @GET()
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getDepartmentById(@PathParam("id") Long id) {
        return Response.ok().entity(departmentService.getEmployeeInDepartment(id)).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createDepartment(CreateDepartmentDTO createDepartmentDTO) {
        return Response.ok().entity(departmentService.createDepartment(createDepartmentDTO)).build();
    }

}
