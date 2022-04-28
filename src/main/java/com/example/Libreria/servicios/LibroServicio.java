package com.example.Libreria.servicios;

import com.example.Libreria.entidades.Autor;
import com.example.Libreria.entidades.Editorial;
import com.example.Libreria.entidades.Libro;
import com.example.Libreria.errores.ErrorServicio;
import com.example.Libreria.repositorio.AutorRepositorio;
import com.example.Libreria.repositorio.EditorialRepositorio;
import com.example.Libreria.repositorio.LibroRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio LibroRepositorio;

    @Autowired
    private AutorRepositorio AutorLibroRepositorio;

    @Autowired
    private EditorialRepositorio EditorialRepositorio;

    @Transactional(propagation = Propagation.NESTED)

    public void crearLibro(String idA, String idE, Integer isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Autor autor, Editorial editorial) throws ErrorServicio {

        validarLibro(isbn, titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, autor, editorial);

        Libro libro = new Libro();

        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(0);
        libro.setEjemplaresRestantes(libro.getEjemplares() - libro.getEjemplaresPrestados());
        libro.setAlta(true);

       
        LibroRepositorio.save(libro);

    }

    @Transactional(propagation = Propagation.NESTED)
    public void modificarLibro(String id, Integer isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Boolean alta) throws ErrorServicio {

        Optional<Libro> libroABuscar = LibroRepositorio.findById(id);

        if (libroABuscar.isPresent()) {
            Libro libro = libroABuscar.get();
            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(libro.getEjemplares() - libro.getEjemplaresPrestados());
            libro.setAlta(alta);

            Optional<Autor> autorABuscar = AutorLibroRepositorio.findById(id);

            if (autorABuscar.isPresent()) {
                libro.setAutor(autorABuscar.get());
            } else {
                throw new ErrorServicio("El autor no ha sido encontrado");
            }

            Optional<Editorial> editorialABuscar = EditorialRepositorio.findById(id);

            if (editorialABuscar.isPresent()) {
                libro.setEditorial(editorialABuscar.get());
            } else {
                throw new ErrorServicio("La editorial no ha sido encontrada");
            }

            LibroRepositorio.save(libro);

        } else {
            throw new ErrorServicio("El libro no ha sido encontrado");
        }

    }
    @Transactional
    public void darAltaALibro(String id, Integer isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Boolean alta) throws ErrorServicio {
        Optional<Libro> libroABuscar = LibroRepositorio.findById(id);

        if (libroABuscar.isPresent()) {
            Libro libro = libroABuscar.get();
            libro.setAlta(true);

            LibroRepositorio.save(libro);

        } else {
            throw new ErrorServicio("El libro no ha sido encontrado");
        }
    }

    public void validarLibro(Integer isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes,  Autor autor, Editorial editorial) throws ErrorServicio {
        if (isbn == null || isbn.toString().length() > 13) {
            throw new ErrorServicio("El ISBN no puede tener mas de 13 digitos");
        }
        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("El titulo no puede estar vacío");
        }
        if (anio == null || anio.toString().length() > 4) {
            throw new ErrorServicio("El anio no puede tener más de 4 dígitos");
        }
        if (autor == null) {
            throw new ErrorServicio("El autor no puede estar vacío");
        }
        if (editorial == null) {
            throw new ErrorServicio("La editorial no puede estar vacía");
        }
    }
    
    public List<Libro> listadoLibros() {
        return (List<Libro>) LibroRepositorio.findAll();
    }

    public Optional<Libro> buscaLibros(String id) {
        return (Optional<Libro>) LibroRepositorio.findById(id);
    }

    public Libro buscaLibrosPorTitulo(String titulo) {
        return LibroRepositorio.BuscarPorId(titulo);
    }

}
