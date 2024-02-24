package org.eclipse.jakarta.hello.base.exception;

import lombok.Getter;
import org.eclipse.jakarta.hello.base.exception.body.BodyErrorResponse;

import javax.ejb.ApplicationException;

@Getter
@ApplicationException
public abstract class AppException extends Exception{
    private BodyErrorResponse bodyErrorResponse;
    public AppException(int statusCode, String errorKey, String message) {
        super(message);
        this.bodyErrorResponse = new BodyErrorResponse(false, statusCode, errorKey, message);
    }
}
