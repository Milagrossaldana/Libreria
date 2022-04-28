package com.example.Libreria.servicios;

import com.example.Libreria.entidades.Autor;
import com.example.Libreria.errores.ErrorServicio;
import com.example.Libreria.repositorio.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio AutorRepositorio;

    @Transactional(propagation = Propagation.NESTED, rollbackFor = {Exception.class})
    public void crearAutor(String nombre) throws ErrorServicio {

        validarAutor(nombre);

        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setAlta(true);

        AutorRepositorio.save(autor);
    }

    public void validarAutor(String nombre) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacío");
        }
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = {Exception.class})
    public void modifiarAutor(String id, String nombre) throws ErrorServicio {

        Optional<Autor> autorABuscar = AutorRepositorio.findById(id);

        if (autorABuscar.isPresent()) {
            Autor autor = autorABuscar.get();
            autor.setNombre(nombre);

            AutorRepositorio.save(autor);
        } else {

            throw new ErrorServicio("El autor no ha sido encontrado");

        }

    }
    @Transactional(propagation = Propagation.NESTED, rollbackFor = {Exception.class})
    public void darBajaAutor(String id) throws ErrorServicio {

        Optional<Autor> autorABuscar = AutorRepositorio.findById(id);

        if (autorABuscar.isPresent()) {
            Autor autor = autorABuscar.get();
            autor.setAlta(false);
            AutorRepositorio.save(autor);
        } else {

            throw new ErrorServicio("El autor no ha sido encontrado");

        }

    }
    @Transactional(propagation = Propagation.NESTED, rollbackFor = {Exception.class})
    public void darAltaAutor(String id, String nombre, Boolean alta) throws ErrorServicio {

        Optional<Autor> autorABuscar = AutorRepositorio.findById(id);

        if (autorABuscar.isPresent()) {
            Autor autor = autorABuscar.get();
            autor.setAlta(true);
            AutorRepositorio.save(autor);
        } else {

            throw new ErrorServicio("El autor no ha sido encontrado");

        }

    }
    @Transactional(readOnly = true)
    public List<Autor> listadoAutor() {
        return (List<Autor>) AutorRepositorio.findAll();
       
    }
    @Transactional(readOnly = true)
    public Autor buscarPorNombre(String nombre) {
        return AutorRepositorio.BuscarPorNombre(nombre);
    }
    @Transactional(readOnly = true)
    public Optional<Autor> buscarPorId(String id) {
        return AutorRepositorio.findById(id);
    }

}
