package br.com.tatamedobem.controller;

import br.com.tatamedobem.dto.AuthLoginRequest;
import br.com.tatamedobem.dto.AuthRegisterRequest;
import br.com.tatamedobem.dto.AuthResponse;
import br.com.tatamedobem.dto.UserResponse;
import br.com.tatamedobem.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticacao")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar usuario para acesso ao sistema")
    public AuthResponse register(@Valid @RequestBody AuthRegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    @Operation(summary = "Autenticar com CPF e senha")
    public AuthResponse login(@Valid @RequestBody AuthLoginRequest request, HttpServletRequest servletRequest) {
        return authService.login(
                request,
                resolveIpAddress(servletRequest),
                servletRequest.getHeader("User-Agent")
        );
    }

    @GetMapping("/users")
    @Operation(summary = "Listar usuarios cadastrados")
    public List<UserResponse> findAllUsers() {
        return authService.findAllUsers();
    }

    private String resolveIpAddress(HttpServletRequest request) {
        String forwardedFor = request.getHeader("X-Forwarded-For");
        if (forwardedFor != null && !forwardedFor.isBlank()) {
            return forwardedFor.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}
