package org.example.crudlibro;

import Modelo.Libro;
import Modelo.LibroDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "libroServlet", value = "/libroServlet")
public class LibroServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        LibroDAO libroDAO = new LibroDAO();

        try {
            switch (accion) {
                case "insertar":
                    libroDAO.insertarLibro(new Libro(isbn, titulo, autor));
                    out.println("<p>Libro insertado correctamente.</p>");
                    break;
                case "actualizar":
                    libroDAO.actualizarLibro(new Libro(isbn, titulo, autor));
                    out.println("<p>Libro actualizado correctamente.</p>");
                    break;
                case "eliminar":
                    libroDAO.eliminarLibro(isbn);
                    out.println("<p>Libro eliminado correctamente.</p>");
                    break;
                case "leer":
                    Libro libro = libroDAO.leerLibro(isbn);
                    out.println("<p>Libro: " + libro + "</p>");
                    break;
                case "leerTodos":
                    out.println("<h3>Todos los Libros:</h3>");
                    for (Libro l : libroDAO.leerTodosLosLibros()) {
                        out.println("<p>" + l + "</p>");
                    }
                    break;
                default:
                    out.println("<p>Acción no válida.</p>");
            }
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
}

