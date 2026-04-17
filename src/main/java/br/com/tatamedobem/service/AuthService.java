package br.com.tatamedobem.service;

import br.com.tatamedobem.domain.AppUser;
import br.com.tatamedobem.domain.UserRole;
import br.com.tatamedobem.dto.AuthLoginRequest;
import br.com.tatamedobem.dto.AuthRegisterRequest;
import br.com.tatamedobem.dto.AuthResponse;
import br.com.tatamedobem.dto.UserResponse;
import br.com.tatamedobem.repository.AppUserRepository;
import br.com.tatamedobem.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse register(AuthRegisterRequest request) {
        String normalizedCpf = normalizeCpf(request.cpf());
        if (appUserRepository.existsByCpf(normalizedCpf)) {
            throw new IllegalArgumentException("Ja existe um usuario cadastrado com este CPF.");
        }

        AppUser user = new AppUser();
        user.setFullName(request.fullName());
        user.setCpf(normalizedCpf);
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setRole(request.role() == null ? UserRole.STAFF : request.role());
        user.setActive(true);

        AppUser savedUser = appUserRepository.save(user);
        return buildAuthResponse(savedUser);
    }

    @Transactional(readOnly = true)
    public AuthResponse login(AuthLoginRequest request) {
        String normalizedCpf = normalizeCpf(request.cpf());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(normalizedCpf, request.password())
        );

        AppUser user = appUserRepository.findByCpf(normalizedCpf)
                .orElseThrow(() -> new IllegalArgumentException("Usuario nao encontrado."));
        return buildAuthResponse(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> findAllUsers() {
        return appUserRepository.findAll().stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getFullName(),
                        user.getCpf(),
                        user.getRole(),
                        user.getActive()
                ))
                .toList();
    }

    public String normalizeCpf(String cpf) {
        String normalized = cpf.replaceAll("\\D", "");
        if (normalized.length() != 11) {
            throw new IllegalArgumentException("CPF deve conter 11 digitos.");
        }
        return normalized;
    }

    private AuthResponse buildAuthResponse(AppUser user) {
        return new AuthResponse(
                jwtService.generateToken(user),
                "Bearer",
                user.getId(),
                user.getFullName(),
                user.getCpf(),
                user.getRole()
        );
    }
}
