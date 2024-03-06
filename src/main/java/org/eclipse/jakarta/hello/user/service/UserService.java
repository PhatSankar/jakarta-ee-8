package org.eclipse.jakarta.hello.user.service;

import org.eclipse.jakarta.hello.base.exception.BadRequestException;
import org.eclipse.jakarta.hello.base.exception.NotFoundExeption;
import org.eclipse.jakarta.hello.security.payload.JwtPayload;
import org.eclipse.jakarta.hello.security.tokenprovider.TokenProvider;
import org.eclipse.jakarta.hello.user.dao.UserDAO;
import org.eclipse.jakarta.hello.user.dto.LoginRequestDTO;
import org.eclipse.jakarta.hello.user.dto.LoginResponseDTO;
import org.eclipse.jakarta.hello.user.dto.RegisterResponseDTO;
import org.eclipse.jakarta.hello.user.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserService {

    @Inject
    private UserDAO userDAO;

    @Inject
    private TokenProvider tokenProvider;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws NotFoundExeption, BadRequestException {
        User user = userDAO.findUserByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() ->  new NotFoundExeption("Email account not existed"));

        boolean isValidPassword = BCrypt.checkpw(loginRequestDTO.getPassword(), user.getPassword());
        if (!isValidPassword) {
            throw new BadRequestException("Email or password not valid");
        }
        return new LoginResponseDTO(generateAccessToken(user));
    }

    private String generateAccessToken(User user) {
        JwtPayload jwtPayload = new JwtPayload(user.getEmail());
        String token = tokenProvider.generateToken(jwtPayload.toMap());
        return token;
    }

    public RegisterResponseDTO register(LoginRequestDTO loginRequestDTO) throws BadRequestException {
        if(userDAO.findUserByEmail(loginRequestDTO.getEmail()).isPresent()) {
            throw new BadRequestException("Account existed");
        }
        System.out.println("hehe");
        userDAO.add(new User(loginRequestDTO.getEmail(),
                BCrypt.hashpw(loginRequestDTO.getPassword(), BCrypt.gensalt())));
        return new RegisterResponseDTO(loginRequestDTO.getEmail());
    }
}
