package com.instituto.endoscopia.repository;

import com.instituto.endoscopia.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsernameAndPassword(String username, String password);
    Usuario findByEmail(String email);
    Usuario findByUsername(String username);
}