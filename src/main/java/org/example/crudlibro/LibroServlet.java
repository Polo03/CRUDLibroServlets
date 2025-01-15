package org.example.crudlibro;

import Controlador.ControladorLibro;
import Modelo.Libro;
import Modelo.LibroDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "libroServlet", value = "/libroServlet")
public class LibroServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ObjectMapper conversorJson = new ObjectMapper();
        conversorJson.registerModule(new JavaTimeModule());
        String json_response;
        ControladorLibro controlador = new ControladorLibro();

        try {
            switch (accion) {
                case "insertar":
                    controlador.addLibro(new Libro(isbn, titulo, autor));
                    out.println("<p>Libro insertado correctamente.</p>");
                    break;
                case "actualizar":
                    controlador.updateLibro(new Libro(isbn, titulo, autor));
                    out.println("<p>Libro actualizado correctamente.</p>");
                    break;
                case "eliminar":
                    controlador.deleteLibro(controlador.getLibroByIsbn(isbn));
                    out.println("<p>Libro eliminado correctamente.</p>");
                    break;
                case "leerPorIsbn":
                    Libro libroIsbn = controlador.getLibroByIsbn(isbn);
                    out.println("<p> En java->Libro: " + libroIsbn + "</p>");
                    json_response = conversorJson.writeValueAsString(libroIsbn);
                    out.println("<p> En java json->Libro: " + json_response + "</p>");
                    break;
                case "leerPorTitulo":
                    Libro libroTitulo = controlador.getLibroByTitulo(titulo);
                    out.println("<p> En java->Libro: " + libroTitulo + "</p>");
                    json_response = conversorJson.writeValueAsString(libroTitulo);
                    out.println("<p> En java json->Libro: " + json_response + "</p>");
                    break;
                case "leerTodos":
                    out.println("<h3>Todos los Libros:</h3>");

                    List<Libro> listaLibros  = controlador.getAllLibros();
                    out.println("<p> En java" + listaLibros +"</p>");

                    json_response = conversorJson.writeValueAsString(listaLibros);
                    out.println("<p> En java json" + json_response +"</p>");
                    break;
                default:
                    out.println("<p>Acción no válida.</p>");
            }
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
}

