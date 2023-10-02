package com.gustavo.gestionusuarios.dao;

import com.gustavo.gestionusuarios.models.Usuario;

import java.util.List;

public interface UsuarioDao {

    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registrar(Usuario usuario);

    Long obtenerUsuarioPorCredenciales(Usuario usuario);
}
