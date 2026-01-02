package com.henrique.majesty.service.auth;

import com.henrique.majesty.dto.auth.LoginDto;
import com.henrique.majesty.dto.auth.RegisterDto;
import com.henrique.majesty.dto.auth.TokenDto;
import com.henrique.majesty.entity.auth.UserEntity;
import com.henrique.majesty.repository.user.UserRepository;
import com.henrique.majesty.infrastructure.security.jwt.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager,
                       PasswordEncoder passwordEncoder,
                       UserRepository userRepository,
                       JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public TokenDto login(LoginDto dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.username(), dto.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Set<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        String token = jwtService.generateToken(userDetails.getUsername(), roles);
        Instant expiresAt = jwtService.getExpiryFromNow();

        return new TokenDto(token, expiresAt);
    }

    @Transactional
    public void register(RegisterDto dto) {
        if (userRepository.existsByUsername(dto.username())) {
            throw new IllegalArgumentException("Username already exists");
        }

        UserEntity user = new UserEntity(
                dto.username(),
                passwordEncoder.encode(dto.password())
        );

        user.getRoles().add("ROLE_REPRESENTANTE");

        userRepository.save(user);

    }
}
