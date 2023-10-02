package com.gustavo.gestionusuarios.controllers;

import com.gustavo.gestionusuarios.dao.UsuarioDao;
import com.gustavo.gestionusuarios.models.Usuario;
import com.gustavo.gestionusuarios.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    private String login(@RequestBody Usuario usuario) {
        Long id = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
        if (id != null) {
            return jwtUtil.create(String.valueOf(id), usuario.getEmail());
        }

        return "Fail";
    }
}
