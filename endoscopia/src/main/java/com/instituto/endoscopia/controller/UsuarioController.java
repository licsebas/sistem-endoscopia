package com.instituto.endoscopia.controller;

import com.instituto.endoscopia.model.Usuario;
import com.instituto.endoscopia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Obtener todos los usuarios (para la tabla del administrador)
    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.obtenerTodosLosUsuarios();
    }

    // Obtener un usuario específico por su nombre (para "Mi Perfil")
    @GetMapping("/{username}")
    public Usuario obtenerPorUsername(@PathVariable String username) {
        return usuarioService.obtenerPorUsername(username);
    }

    // Crear un usuario nuevo
    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.guardarUsuario(usuario);
    }

    // Validar el inicio de sesión
    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestParam String username, @RequestParam String password) {
        Usuario u = usuarioService.validarLogin(username, password);
        if (u != null) {
            return new ResponseEntity<>(u, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // Recuperar la contraseña
    @PostMapping("/recuperar")
    public ResponseEntity<String> recuperarContrasena(@RequestParam String email) {
        String msj = usuarioService.recuperarContrasena(email);
        if (msj.contains("Error")) {
            return new ResponseEntity<>(msj, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(msj, HttpStatus.OK);
    }

    // Suspender o Reactivar un usuario
    @PostMapping("/{id}/estado")
    public void cambiarEstadoUsuario(@PathVariable Long id) {
        usuarioService.cambiarEstadoUsuario(id);
    }

    // Cambiar la contraseña propia
    @PostMapping("/cambiar-clave")
    public void cambiarClave(@RequestParam String username, @RequestParam String nuevaClave) {
        usuarioService.cambiarClave(username, nuevaClave);
    }

    // Recibir la edición completa desde la tabla del Administrador
    @PutMapping("/editar/{id}")
    public void editarUsuarioCompleto(@PathVariable Long id, @RequestBody Usuario datos) {
        usuarioService.editarUsuarioCompleto(id, datos);
    }

    // Recibir la actualización de datos desde "Mi Perfil"
    @PutMapping("/mi-perfil/{username}")
    public void actualizarMiPerfil(@PathVariable String username, @RequestBody Usuario datos) {
        usuarioService.actualizarMiPerfil(username, datos);
    }
}