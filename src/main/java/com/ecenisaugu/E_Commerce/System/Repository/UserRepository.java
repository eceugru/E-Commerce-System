package com.ecenisaugu.E_Commerce.System.Repository;

import com.ecenisaugu.E_Commerce.System.Entity.UserEntites.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

     Optional<User> findByEmail(String email);

}
