package pl.domanski.carrent.security.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.domanski.carrent.security.model.RentUserDetails;
import pl.domanski.carrent.security.model.User;
import pl.domanski.carrent.security.model.UserRole;
import pl.domanski.carrent.common.repository.UserRepository;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final long expireTime;
    private final String secret;

    public LoginController(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           @Value("${jwt.expirationTime}") long expireTime,
                           @Value("${jwt.secret}") String secret) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.expireTime = expireTime;
        this.secret = secret;
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginCredentials loginCredentials) {
        return authenticate(loginCredentials.getUsername(), loginCredentials.getPassword());
    }

    @PostMapping("/register")
    public Token register(@RequestBody @Valid RegisterCredentials registerCredentials) {
        if(!checkPasswords(registerCredentials)) {
            throw new IllegalArgumentException("Hasła nie są identyczne");
        }

        if (userRepository.existsByUsername(registerCredentials.username)) {
            throw new IllegalArgumentException("Użytkownik o podanym email już istnieje");
        }

        userRepository.save(User.builder()
                        .username(registerCredentials.username)
                        .password("{bcrypt}" + new BCryptPasswordEncoder().encode(registerCredentials.password))
                        .enabled(true)
                        .firstName(registerCredentials.firstName)
                        .lastName(registerCredentials.lastName)
                        .phone(registerCredentials.phone)
                        .authorities(List.of(UserRole.ROLE_CUSTOMER))
                .build());
        return authenticate(registerCredentials.username, registerCredentials.password);
    }

    private Token authenticate(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow();

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getId(), password)
        );

        RentUserDetails principal = (RentUserDetails) authenticate.getPrincipal();

        String token = JWT.create()
                .withSubject(String.valueOf(principal.getId()))
                .withExpiresAt(new Date(System.currentTimeMillis() + expireTime))
                .sign(Algorithm.HMAC256(secret));

        Boolean adminAccess = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .filter(s -> UserRole.ROLE_ADMIN.getRole().equals(s))
                .map(s -> true)
                .findFirst()
                .orElse(false);

        Boolean workerAccess = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .filter(s -> UserRole.ROLE_WORKER.name().equals(s))
                .map(s -> true)
                .findFirst()
                .orElse(false);

        return new Token(token, adminAccess, workerAccess);
    }


    private static boolean checkPasswords(RegisterCredentials registerCredentials) {
        return registerCredentials.password.equals(registerCredentials.repeatPassword);
    }

    @Getter
    private static class LoginCredentials {
        private String username;
        private String password;
    }

    @Getter
    private static class RegisterCredentials {
        @Email
        @NotBlank
        private String username;
        @NotBlank
        @Length(min=6)
        private String password;
        @NotBlank
        private String repeatPassword;
        @NotBlank
        private String firstName;
        @NotBlank
        private String lastName;
        @Length(min = 9, max = 9)
        private String phone;
    }

    @Getter
    @AllArgsConstructor
    private static class Token {
        private String token;
        private boolean adminAccess;
        private boolean workerAccess;
    }
}
