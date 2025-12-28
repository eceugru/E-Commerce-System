package com.ecenisaugu.E_Commerce.System.Repository;

import com.ecenisaugu.E_Commerce.System.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles,String> {
    Optional<Roles> findByRoleName(String roleName);

}
