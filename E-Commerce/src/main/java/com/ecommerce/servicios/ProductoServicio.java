/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.servicios;

import com.ecommerce.entidades.Producto;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */

public interface ProductoServicio {
    
    public Producto save(Producto producto);
    public Optional<Producto> get(String id);
    public void update(Producto producto);
    public void delete(String id);
    
    
    
}
