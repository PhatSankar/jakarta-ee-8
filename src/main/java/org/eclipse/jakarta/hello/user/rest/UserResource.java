package org.eclipse.jakarta.hello.user.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.eclipse.jakarta.hello.base.exception.BadRequestException;
import org.eclipse.jakarta.hello.base.exception.NotFoundExeption;
import org.eclipse.jakarta.hello.project.dto.ProjectDTO;
import org.eclipse.jakarta.hello.user.dto.LoginRequestDTO;
import org.eclipse.jakarta.hello.user.dto.LoginResponseDTO;
import org.eclipse.jakarta.hello.user.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Api(value = "Authentication api")
@Path("/auth")
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    @Path("/login")
    @ApiOperation(value = "Login")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Login successfully",
                    response = LoginResponseDTO.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "Request sent to the server is invalid"
            ),
            @ApiResponse(
                    code = 500,
                    message = "Request cannot be fulfilled through browser due to server-side problems"
            )
    })
    public Response login(LoginRequestDTO loginRequestDTO) throws NotFoundExeption, BadRequestException {
        return Response.ok().entity(userService.login(loginRequestDTO)).build();
    }

    @POST
    @Path("/register")
    @ApiOperation(value = "Login")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "register successfully",
                    response = Object.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "Request sent to the server is invalid"
            ),
            @ApiResponse(
                    code = 500,
                    message = "Request cannot be fulfilled through browser due to server-side problems"
            )
    })
    public Response register(LoginRequestDTO loginRequestDTO) throws BadRequestException {
        return Response.ok().entity(userService.register(loginRequestDTO)).build();
    }

}
