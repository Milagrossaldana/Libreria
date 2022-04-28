package com.example.Libreria.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class clienteControlador {
    
    @GetMapping("/Registro")
    public String registro() {
        return "Registro";
    } 
    @GetMapping("/Registrar")
    public String registrar() {
        return "Registrar";
    }
    
   
}
