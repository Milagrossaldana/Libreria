package com.example.Libreria.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class editorialControlador {
    
    
    @GetMapping("/Editorial")
    public String Editorial() {
        return "Editorial";
    }
}
