package org.eclipse.jakarta.hello.base.filters;

import lombok.SneakyThrows;
import org.eclipse.jakarta.hello.base.exception.UnauthorizedException;
import org.eclipse.jakarta.hello.base.exception.body.BodyErrorResponse;
import org.eclipse.jakarta.hello.security.payload.JwtPayload;
import org.eclipse.jakarta.hello.security.tokenprovider.TokenProvider;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Secure
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Inject
    TokenProvider tokenProvider;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String token = getTokenFromHeader(containerRequestContext);

        JwtPayload payload = getPayloadFromToken(token);

        containerRequestContext.setSecurityContext(new RequestSecurityContext(payload));
    }

    @SneakyThrows
    private JwtPayload getPayloadFromToken(String token) {
        try {
            return JwtPayload.fromMap(tokenProvider.validateToken(token));
        }
        catch (UnauthorizedException e) {
            throw new UnauthorizedException(e.getMessage());
        }
    }

    private String getTokenFromHeader(ContainerRequestContext requestContext) {
        String authHeader = requestContext.getHeaderString(AUTHORIZATION_HEADER);
        if (authHeader == null || !authHeader.startsWith("Bearer") || authHeader.split(" ")[1].isEmpty()) {
            BodyErrorResponse bodyErrorResponse = new BodyErrorResponse(
                    false,
                    Response.Status.FORBIDDEN.getStatusCode(),
                    Response.Status.FORBIDDEN.getReasonPhrase(),
                    "Permission denied"
            );
            requestContext.abortWith(
                    Response.status(bodyErrorResponse.getStatusCode())
                            .type(MediaType.APPLICATION_JSON)
                            .entity(bodyErrorResponse)
                            .build());
            return null;
        }

        return authHeader.split(" ")[1].trim();
    }


}
