package com.example.book_store_security.Services;

import com.example.book_store_security.Repositories.UsersRepository;
import com.example.book_store_security.domains.Users;
import com.example.book_store_security.dtos.Permissions.AuthenticationDTO;
import com.example.book_store_security.dtos.Permissions.LoginResponseDTO;
import com.example.book_store_security.dtos.Permissions.RegisterDTO;
import com.example.book_store_security.security.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    public AuthenticationService(UsersRepository usersRepository, @Lazy AuthenticationManager authenticationManager, TokenService tokenService) {
        this.usersRepository = usersRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByLogin(username);
    }


    public LoginResponseDTO userLogin(AuthenticationDTO data)
    {
        if (usersRepository.findByLogin(data.login()) == null) throw new UsernameNotFoundException("Esse login não existe.");
        var authentication = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(authentication);

        var token = tokenService.generate((Users) auth.getPrincipal());

        return new LoginResponseDTO(token);
    }

    public Users register(RegisterDTO registerDTO){

        if(usersRepository.findByLogin(registerDTO.login())!= null) throw new RuntimeException("Esse login ja existe.");
        String passwordEncoder = new BCryptPasswordEncoder().encode(registerDTO.password());
        Users userSave = new Users(registerDTO.login(), passwordEncoder, registerDTO.role());

        return usersRepository.save(userSave);
    }
}
