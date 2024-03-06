package org.eclipse.jakarta.hello.base.filters;

import lombok.AllArgsConstructor;
import org.eclipse.jakarta.hello.security.payload.JwtPayload;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;


@AllArgsConstructor
public class RequestSecurityContext implements SecurityContext {

    JwtPayload jwtPayload;
    @Override
    public Principal getUserPrincipal() {
        return jwtPayload;
    }

    @Override
    public boolean isUserInRole(String s) {
        return false;
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public String getAuthenticationScheme() {
        return null;
    }
}
