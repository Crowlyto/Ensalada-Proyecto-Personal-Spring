/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.controladores;

import com.ecommerce.entidades.Usuario;
import com.ecommerce.servicios.IUsuarioServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author crowl
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
    private final Logger log=LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    private IUsuarioServicio servU;
    
    
    @GetMapping("/registro")
    public String create(){
        return "usuario/registro";
    }
    
    @PostMapping("/save")
    public String save(Usuario usuario){
        log.info("Usuario registro: {}", usuario);
        
        usuario.setRol("USER");
        servU.save(usuario);
        return "redirect:/";
    }
}