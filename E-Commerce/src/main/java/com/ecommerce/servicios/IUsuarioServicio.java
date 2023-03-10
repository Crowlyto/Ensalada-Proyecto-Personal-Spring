/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.servicios;

import com.ecommerce.entidades.Usuario;
import java.util.List;
import java.util.Optional;


/**
 *
 * @author crowl
 */

public interface IUsuarioServicio {
    Optional<Usuario> findById(Integer id);
    Usuario save(Usuario usuario);
    Optional<Usuario>findByEmail(String email);
    List<Usuario> findAll();
    
}
