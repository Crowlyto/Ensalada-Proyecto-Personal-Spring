/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.controladores;

import com.ecommerce.entidades.Producto;
import com.ecommerce.entidades.Usuario;
import com.ecommerce.servicios.ProductoServicio;
import com.ecommerce.servicios.UploadFileServicio;
import java.io.IOException;
import java.util.Optional;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private UploadFileServicio upload;

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
    public String save(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {

        Usuario user = new Usuario(1, "", "", "", "", "", "");
        producto.setUsuario(user);
        //imagen
        if (producto.getId() == null) {//cuando se crea un producto
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        } else {
            if (file.isEmpty()) {//cuando se edita el producto pero no cambiamos la imagen
                Producto p = new Producto();
                p = servP.get(producto.getId()).get();
                producto.setImagen(p.getImagen());

            } else {
                  String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
            }
        }
        servP.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        Producto producto = new Producto();
        Optional<Producto> respuesta = servP.get(id);
        producto = respuesta.get();
        System.out.println(producto.getNombre());
        model.addAttribute("producto", producto);
        return "productos/edit";
    }

    @PostMapping("/update")
    public String update(Producto producto) {
        servP.update(producto);
        return "redirect:/productos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        servP.delete(id);
        return "redirect:/productos";
    }

}
