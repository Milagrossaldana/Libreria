package com.example.Libreria.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class libroControlador {
     
    
    @GetMapping("/Libro")
    public String Libro() {
        return "Libro";
    }
}
