package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static String url = "jdbc:mariadb://localhost:3306/biblioteca";
    private static String user = "Carlos";
    private static String pass = "123";
    private static Connection con = null;

    public static Connection getConexion() {
        if (con == null) {
            try {
                con = DriverManager.getConnection(url, user, pass);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return con;
    }

}