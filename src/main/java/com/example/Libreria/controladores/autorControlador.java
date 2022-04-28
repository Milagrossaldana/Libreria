package com.example.Libreria.controladores;

import com.example.Libreria.entidades.Autor;
import com.example.Libreria.errores.ErrorServicio;
import com.example.Libreria.servicios.AutorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class autorControlador {
    @Autowired
    AutorServicio autorServicio;
    
    @GetMapping("/Autor")
    public String Autor() {
        return "Autor";
    }
    
     @GetMapping("/modificarAutor")
      public String modificarAutor(String id,ModelMap modelo) {
        List<Autor> autores = autorServicio.listadoAutor();
        modelo.put("autores", autores);
        return "modificarAutor";
    }
    
    @PostMapping("/RegistrarAutor")
    public String RegistrarAutor(@RequestParam String nombre, ModelMap modelo) throws ErrorServicio{
        System.out.println("Nombre: " + nombre);
        autorServicio.crearAutor(nombre);
        return "cargarAutor.html";
    }
    
    @PostMapping("/ModificarAutor")
    public String ModificarAutor(@RequestParam String id, ModelMap modelo) throws ErrorServicio{
        System.out.println("Nombre: " + id);
        
        return "ModificarAutor.html";
    }
}
