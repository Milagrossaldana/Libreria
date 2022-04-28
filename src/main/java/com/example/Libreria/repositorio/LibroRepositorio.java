/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Libreria.repositorio;

import com.example.Libreria.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface LibroRepositorio extends JpaRepository <Libro,String> {
    
    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    public Libro BuscarPorNombre(@Param("titulo")String nombre);
    
    @Query("SELECT l FROM Libro l WHERE l.id = :id")
    public Libro BuscarPorId(@Param("id")String id);
    
    
}
