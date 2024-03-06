package org.eclipse.jakarta.hello.security.payload;

import lombok.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JwtPayload implements Principal {
    private static final String EMAIL_KEY = "email";

    private String email;

    public Map<String, String> toMap() {
        Map<String, String> result = new HashMap<>();
        result.put(EMAIL_KEY, email);
        return result;
    }

    public static JwtPayload fromMap(Map<String, String> map) {
        JwtPayload jwtPayload = new JwtPayload();
        jwtPayload.setEmail(map.get(JwtPayload.EMAIL_KEY));

        return jwtPayload;
    }

    @Override
    public String getName() {
        return email;
    }
}
