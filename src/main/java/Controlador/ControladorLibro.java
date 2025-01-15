package Controlador;

import Modelo.Libro;
import Modelo.LibroDAO;

import java.util.List;

public class ControladorLibro {

    private LibroDAO libroDAO;

    public ControladorLibro() {
        libroDAO = new LibroDAO();
    }

    public boolean addLibro(Libro libro) {
        return libroDAO.addLibro(libro);
    }

    public Libro getLibroByIsbn(String isbn) {
        return libroDAO.getLibroByIsbn(isbn);
    }

    public Libro getLibroByTitulo(String titulo) {
        return libroDAO.getLibroByTitulo(titulo);
    }

    public List<Libro> getAllLibros(){
        return libroDAO.getAllLibros();
    }

    public Libro updateLibro(Libro libro) {
        return libroDAO.updateLibro(libro);
    }

    public boolean deleteLibro(Libro libro) {
        return libroDAO.deleteLibro(libro);
    }
}
