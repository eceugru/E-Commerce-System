package com.ecenisaugu.E_Commerce.System.Controller;

import com.ecenisaugu.E_Commerce.System.Dto.Request.Authentication.LoginDto;
import com.ecenisaugu.E_Commerce.System.Dto.Request.Authentication.RegisterDto;
import com.ecenisaugu.E_Commerce.System.Service.concretes.AuthenticationService;
import jakarta.servlet.Registration;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @GetMapping("/me")
    public UserDetails getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterDto registration) {
        return authenticationService.register(registration);

    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        return authenticationService.login(loginDto);
    }


}
