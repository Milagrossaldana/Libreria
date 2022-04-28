package com.example.Libreria.servicios;

import com.example.Libreria.entidades.Editorial;
import com.example.Libreria.errores.ErrorServicio;

import com.example.Libreria.repositorio.EditorialRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class EditorialServicio {

    @Autowired
    private EditorialRepositorio EditorialRepositorio;

    @Transactional
    public void crearEditorial(String nombre, Boolean alta) throws ErrorServicio {

        validarEditorial(nombre, alta);

        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(true);

        EditorialRepositorio.save(editorial);
    }

    public void validarEditorial(String nombre, Boolean alta) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacío");
        }
    }

    @Transactional
    public void modifiarEditorial(String id, String nombre, Boolean alta) throws ErrorServicio {

        Optional<Editorial> editorialABuscar = EditorialRepositorio.findById(id);

        if (editorialABuscar.isPresent()) {
            Editorial editorial = editorialABuscar.get();
            editorial.setNombre(nombre);

            EditorialRepositorio.save(editorial);
        } else {

            throw new ErrorServicio("La editorial no ha sido encontrada");

        }

    }

    @Transactional
    
public void darBajaAutor(String id, String nombre, Boolean baja) throws ErrorServicio {
        Optional<Editorial> editorialABuscar = EditorialRepositorio.findById(id);

        if (editorialABuscar.isPresent()) {
            Editorial editorial = editorialABuscar.get();
            editorial.setBaja(false);
            EditorialRepositorio.save(editorial);
        } else {

            throw new ErrorServicio("La editorial no ha sido encontrada");

        }

    }

    @Transactional
    public void darAltaAutor(String id, String nombre, Boolean alta) throws ErrorServicio {

        Optional<Editorial> editorialABuscar = EditorialRepositorio.findById(id);

        if (editorialABuscar.isPresent()) {
            Editorial editorial = editorialABuscar.get();
            editorial.setAlta(false);
            EditorialRepositorio.save(editorial);
        } else {

            throw new ErrorServicio("La editorial no ha sido encontrada");

        }

    }

    public List<Editorial> listadoEditorial() {
        return (List<Editorial>) EditorialRepositorio.findAll();
    }

    public Editorial buscarPorNombre(String nombre) {
        return EditorialRepositorio.BuscarPorNombre(nombre);
    }

    public Optional<Editorial> buscarPorId(String id) {
        return EditorialRepositorio.findById(id);
    }

}
