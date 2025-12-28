package com.ecenisaugu.E_Commerce.System.Service.concretes;

import com.ecenisaugu.E_Commerce.System.Dto.Request.Authentication.LoginDto;
import com.ecenisaugu.E_Commerce.System.Dto.Request.Authentication.RegisterDto;
import com.ecenisaugu.E_Commerce.System.Entity.Roles;
import com.ecenisaugu.E_Commerce.System.Entity.UserEntites.User;
import com.ecenisaugu.E_Commerce.System.Repository.RolesRepository;
import com.ecenisaugu.E_Commerce.System.Repository.UserRepository;
import com.ecenisaugu.E_Commerce.System.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;


    public String register(RegisterDto dto) {

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        Roles role = rolesRepository.findByRoleName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setRole(role);

        userRepository.save(user);
        return "Registration successful";
    }

    public String login(LoginDto dto) {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
       
        System.out.println("RAW PASS: " + dto.getPassword());
        System.out.println("ENCODED PASS: " + passwordEncoder.encode(dto.getPassword()));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {    // burasaı doğru sırada olmadığı için giriş yapamadık
            throw new RuntimeException("Wrong password");
        }
        
        String token = jwtService.generateToken(user);
        return token;
    }
}

