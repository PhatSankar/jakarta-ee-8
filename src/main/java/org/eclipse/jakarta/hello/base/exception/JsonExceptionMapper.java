package org.eclipse.jakarta.hello.base.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.eclipse.jakarta.hello.base.exception.body.BodyErrorResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonExceptionMapper implements ExceptionMapper<JsonProcessingException> {
    @Override
    public Response toResponse(JsonProcessingException e) {
        BodyErrorResponse body = new BodyErrorResponse(false,
                Response.Status.BAD_REQUEST.getStatusCode(),
                Response.Status.BAD_REQUEST.getReasonPhrase(),
                "Invalid JSON format");
        return Response.status(body.getStatusCode()).entity(body).type(MediaType.APPLICATION_JSON).build();
    }
}
