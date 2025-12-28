package com.ecenisaugu.E_Commerce.System.Security;

import com.ecenisaugu.E_Commerce.System.Repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@RequiredArgsConstructor
public class AppConfiguration {

    private final UserRepository userRepository;

    // ✅ User → Spring Security’nin tanıyacağı hale getirildi (UserDetails).
    @Bean
    public UserDetailsService userDetailsService(){
         return  userName-> userRepository.findByEmail(userName).orElseThrow(()->new UsernameNotFoundException("Email not founded!"));
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // login işleminde kullanılacak ana doğrulayıcı
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
        return configuration.getAuthenticationManager();
    }

    //Gelen username+password bilgisini alın, DB’den user yükleyin ve BCrypt ile şifreyi doğrulayın” diyen yapı.
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService());  // ← sende bu gerekli

        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider;
    }









}
