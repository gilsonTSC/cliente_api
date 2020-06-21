package com.gilsontsc.clientes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gilsontsc.clientes.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}