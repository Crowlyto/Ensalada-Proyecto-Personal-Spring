/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.controladores;

import com.ecommerce.entidades.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ecommerce.servicios.IProductoServicio;
import com.ecommerce.servicios.IUsuarioServicio;

/**
 *
 * @author crowl
 */
@Controller
@RequestMapping("/administrador")
public class AdministradorController {
    @Autowired
    private IProductoServicio servP;
    @Autowired
    private IUsuarioServicio servU;
    
    
    @GetMapping("")
    public String home(Model model){
        List<Producto> productos=servP.findAll();
        model.addAttribute("productos",productos);
        
        return "administrador/home";
    }
    
    @GetMapping("/usuarios")
    public String usuarios(Model model){
        model.addAttribute("usuarios", servU.findAll());
        
        return "administrador/usuarios";
    }
    
}
