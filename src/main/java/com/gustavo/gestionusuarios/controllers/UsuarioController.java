package com.gustavo.gestionusuarios.controllers;

import com.gustavo.gestionusuarios.dao.UsuarioDao;
import com.gustavo.gestionusuarios.models.Usuario;
import com.gustavo.gestionusuarios.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
Controllers: Utilizado para manejar las URL
*/
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.GET)
    private Usuario getUsuario(@PathVariable Long id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Gustavo");
        usuario.setApellido("Cardenas");
        usuario.setEmail("gustavo_cardenas@hotmail.com");
        usuario.setTelefono("8712268852");
        return usuario;
    }

    @RequestMapping(value = "api/usuario")
    public List<Usuario> getUsuario(@RequestHeader(value = "Authorization") String token) {
        return validarToken(token) ? usuarioDao.getUsuarios() : new ArrayList<>();
    }

    @RequestMapping(value = "api/usuario", method = RequestMethod.POST)
    private void insertarUsuario(@RequestBody Usuario usuario) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        usuario.setPassword(argon2.hash(1, 1024, 1, usuario.getPassword()));
        usuarioDao.registrar(usuario);
    }

    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.DELETE)
    private void eliminar(@PathVariable Long id, @RequestHeader(value = "Authorization") String token) {
        if (validarToken(token))
            usuarioDao.eliminar(id);
    }

    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

}
