package Modelo;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    private Connection getConexion() throws SQLException {
        String url = "jdbc:mariadb://localhost:3306/biblioteca";
        String usuario = "Carlos";
        String contraseña = "123";
        return DriverManager.getConnection(url, usuario, contraseña);
    }

    public void insertarLibro(Libro libro) throws SQLException {
        String sql = "INSERT INTO libro (isbn, titulo, autor) VALUES (?, ?, ?)";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, libro.getIsbn());
            ps.setString(2, libro.getTitulo());
            ps.setString(3, libro.getAutor());
            ps.executeUpdate();
        }
    }

    public void actualizarLibro(Libro libro) throws SQLException {
        String sql = "UPDATE libro SET titulo = ?, autor = ? WHERE isbn = ?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getIsbn());
            ps.executeUpdate();
        }
    }

    public void eliminarLibro(String isbn) throws SQLException {
        String sql = "DELETE FROM libro WHERE isbn = ?";
        try (Connection con = getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, isbn);
            ps.executeUpdate();
        }
    }

    public Libro leerLibro(String isbn) throws SQLException {
        String sql = "SELECT * FROM libro WHERE isbn = ?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, isbn);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Libro(rs.getString("isbn"), rs.getString("titulo"), rs.getString("autor"));
                }
            }
        }
        return null;
    }

    public List<Libro> leerTodosLosLibros() throws SQLException {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM libro";
        try (Connection con = getConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                libros.add(new Libro(rs.getString("isbn"), rs.getString("titulo"), rs.getString("autor")));
            }
        }
        return libros;
    }
}

