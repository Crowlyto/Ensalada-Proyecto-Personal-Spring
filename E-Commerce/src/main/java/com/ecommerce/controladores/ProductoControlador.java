/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.controladores;

import com.ecommerce.entidades.Producto;
import com.ecommerce.entidades.Usuario;
import com.ecommerce.servicios.ProductoServicio;
import java.util.Optional;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author crowl
 */
@Controller
@RequestMapping("/productos")
public class ProductoControlador {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoControlador.class);
    @Autowired
    private ProductoServicio servP;

    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("productos", servP.findAll());
        return "productos/show";
    }

    @GetMapping("/create")
    public String create() {
        return "productos/create";
    }

    @PostMapping("/save")
    public String save(Producto producto) {
        LOGGER.info("Este es el objeto producto{}", producto);
        Usuario user = new Usuario(1, "", "", "", "", "", "");
        producto.setUsuario(user);
        servP.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        Producto producto = new Producto();
        Optional<Producto> respuesta = servP.get(id);
        producto = respuesta.get();
        System.out.println(producto.getNombre());
        model.addAttribute("producto",producto);
        return "productos/edit";
    }
    @PostMapping("/update")
    public String update(Producto producto){
        servP.update(producto);
        return "redirect:/productos";
    }

}
