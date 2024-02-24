package org.eclipse.jakarta.hello.base.exception.body;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BodyErrorResponse {

    private boolean success;

    private int statusCode;

    private String errorKey;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

}
