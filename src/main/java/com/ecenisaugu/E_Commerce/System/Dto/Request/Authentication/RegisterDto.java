package com.ecenisaugu.E_Commerce.System.Dto.Request.Authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String roleName;
}
