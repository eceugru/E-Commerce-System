package com.ecenisaugu.E_Commerce.System.Entity;

import com.ecenisaugu.E_Commerce.System.Entity.UserEntites.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Roles")
public class Roles {
    @Id
    @Column(name = "role_id")
    private String roleId;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
