package Modelo;

import jakarta.persistence.*;

import java.util.List;

public class LibroDAO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("unidad-biblioteca");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    //INSERT
    public boolean addLibro(Libro libro){
        tx.begin();
        em.persist(libro);
        tx.commit();
        return false;
    }

    //SELECT WHERE ID
    public Libro getLibroByIsbn(String isbn){
        return em.find(Libro.class, isbn);
    }

    //SELECT WHERE DNI
    public Libro getLibroByTitulo(String titulo){
        return em.find(Libro.class, titulo);
    }
    //SELECT *
    public List<Libro> getAllLibros(){
        return em.createQuery("SELECT l FROM Libro l").getResultList();
    }

    //UPDATE
    public Libro updateLibro(Libro libro){
        tx.begin();
        libro = em.merge(libro);
        tx.commit();
        return libro;
    }
    //DELETE WHERE libro.isbn
    public boolean deleteLibro(Libro libro){
        tx.begin();
        em.remove(libro);
        tx.commit();
        return true;
    }
}
