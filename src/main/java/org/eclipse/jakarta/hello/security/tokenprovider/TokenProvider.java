package org.eclipse.jakarta.hello.security.tokenprovider;

import org.eclipse.jakarta.hello.base.exception.UnauthorizedException;

import javax.ws.rs.ext.Provider;
import java.util.Map;

public interface TokenProvider {
    String generateToken(Map<String, String> payload);

    Map<String, String> validateToken(String token) throws UnauthorizedException;
}
