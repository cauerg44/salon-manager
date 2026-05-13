package br.com.beautycore.api.services;

import br.com.beautycore.api.config.TokenProvider;
import br.com.beautycore.api.dto.request.LoginRequestDTO;
import br.com.beautycore.api.dto.request.RegisterRequestDTO;
import br.com.beautycore.api.dto.response.ProfessionalResponseDTO;
import br.com.beautycore.api.dto.response.TokenResponseDTO;
import br.com.beautycore.api.entity.Professional;
import br.com.beautycore.api.entity.Role;
import br.com.beautycore.api.entity.Specialty;
import br.com.beautycore.api.enums.RoleType;
import br.com.beautycore.api.repository.ProfessionalRepository;
import br.com.beautycore.api.repository.RoleRepository;
import br.com.beautycore.api.services.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ProfessionalRepository professionalRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    private final SpecialtyService specialtyService;

    @Value("${jwt.expiration}")
    private long expirationTime;

    public ProfessionalResponseDTO register(RegisterRequestDTO dto) {
        Professional professional = professionalRepository.findByEmail(dto.email()).orElse(null);

        if (professional != null) {
            throw new DomainException("Profissional já cadastrado com esse email");
        }

        Role role = roleRepository.findByAuthority(RoleType.ROLE_PROFESSIONAL.name())
                .orElseGet(() -> roleRepository.save(Role.builder().authority(RoleType.ROLE_PROFESSIONAL.name()).build()));

        Set<Specialty> specializations = specialtyService.getSpecialtiesByIds(dto.specializationsIds());

        Professional professionalRegistered = professionalRepository.save(Professional.builder()
                .name(dto.name())
                .email(dto.email())
                .isActive(true)
                .isWorking(false)
                .roles(Set.of(role))
                .specializations(specializations)
                .password(passwordEncoder.encode(dto.password()))
                .build()
        );

        return new ProfessionalResponseDTO(professionalRegistered);
    }

    public TokenResponseDTO login(LoginRequestDTO dto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));

            String token = tokenProvider.generateToken(authentication);

            return new TokenResponseDTO(token, expirationTime);
        }
        catch (BadCredentialsException e) {
            throw new DomainException("Credenciais inválidas");
        }
        catch (Exception e) {
            throw(e);
        }
    }
}