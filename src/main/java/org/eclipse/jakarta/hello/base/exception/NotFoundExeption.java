package org.eclipse.jakarta.hello.base.exception;

import lombok.Getter;

import javax.ejb.ApplicationException;
import javax.ws.rs.core.Response;

@Getter
@ApplicationException
public class NotFoundExeption extends AppException {
    public NotFoundExeption(String message) {
        super(Response.Status.NOT_FOUND.getStatusCode(), Response.Status.NOT_FOUND.getReasonPhrase(), message);
    }
}
