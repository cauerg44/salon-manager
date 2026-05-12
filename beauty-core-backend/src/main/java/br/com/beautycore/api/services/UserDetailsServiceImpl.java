package br.com.beautycore.api.services;

import br.com.beautycore.api.repository.ProfessionalRepository;
import br.com.beautycore.api.services.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ProfessionalRepository professionalRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return professionalRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado"));
    }
}
