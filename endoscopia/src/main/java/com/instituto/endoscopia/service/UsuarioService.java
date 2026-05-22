package com.instituto.endoscopia.service;

import com.instituto.endoscopia.model.Usuario;
import com.instituto.endoscopia.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostConstruct
    public void inicializarUsuarios() {
        if (usuarioRepository.count() == 0) {
            Usuario admin = new Usuario();
            admin.setUsername("gerente"); admin.setPassword("admin"); admin.setRol("Administrador");
            admin.setNombreReal("Carlos"); admin.setApellido("Perez"); admin.setDni("123");
            admin.setSexo("Masculino"); admin.setFechaNacimiento("1980-01-01");
            admin.setEmail("admin@clinica.com"); admin.setTelefono("123");
            admin.setPais("Argentina"); admin.setProvincia("Tucumán"); admin.setCiudad("San Miguel de Tucumán");
            usuarioRepository.save(admin);
        }
    }

    public List<Usuario> obtenerTodosLosUsuarios() { return usuarioRepository.findAll(); }
    public Usuario obtenerPorUsername(String username) { return usuarioRepository.findByUsername(username); }
    public Usuario guardarUsuario(Usuario u) { return usuarioRepository.save(u); }

    public Usuario validarLogin(String u, String p) {
        Usuario user = usuarioRepository.findByUsernameAndPassword(u, p);
        return (user != null && (user.getActivo() == null || user.getActivo())) ? user : null;
    }

    public String recuperarContrasena(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) { return "Simulación de correo: Tu clave es " + usuario.getPassword(); }
        return "Error: No se encontró cuenta con ese email.";
    }

    public void cambiarEstadoUsuario(Long id) {
        usuarioRepository.findById(id).ifPresent(u -> {
            u.setActivo(!(u.getActivo() != null ? u.getActivo() : true));
            usuarioRepository.save(u);
        });
    }

    public void cambiarClave(String username, String nuevaClave) {
        Usuario u = usuarioRepository.findByUsername(username);
        if (u != null) { u.setPassword(nuevaClave); usuarioRepository.save(u); }
    }

    // Edición desde Admin
    public void editarUsuarioCompleto(Long id, Usuario d) {
        usuarioRepository.findById(id).ifPresent(u -> {
            u.setUsername(d.getUsername()); u.setRol(d.getRol()); u.setEmail(d.getEmail());
            u.setNombreReal(d.getNombreReal()); u.setApellido(d.getApellido());
            u.setDni(d.getDni()); u.setSexo(d.getSexo()); u.setFechaNacimiento(d.getFechaNacimiento());
            u.setTelefono(d.getTelefono()); u.setPais(d.getPais()); u.setProvincia(d.getProvincia());
            u.setCiudad(d.getCiudad()); u.setDireccion(d.getDireccion());
            usuarioRepository.save(u);
        });
    }

    // Edición Propia (Mi Perfil)
    public void actualizarMiPerfil(String username, Usuario d) {
        Usuario u = usuarioRepository.findByUsername(username);
        if (u != null) {
            u.setNombreReal(d.getNombreReal()); u.setApellido(d.getApellido());
            u.setDni(d.getDni()); u.setSexo(d.getSexo()); u.setFechaNacimiento(d.getFechaNacimiento());
            u.setTelefono(d.getTelefono()); u.setPais(d.getPais()); u.setProvincia(d.getProvincia());
            u.setCiudad(d.getCiudad()); u.setDireccion(d.getDireccion()); u.setEmail(d.getEmail());
            usuarioRepository.save(u);
        }
    }
}