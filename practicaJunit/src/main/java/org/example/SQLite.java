package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLite {
    private static final String DB_URL = "jdbc:sqlite:usuarios.db";

    // Constructor para inicializar la conexión y crear la tabla si no existe
    public SQLite() {
        try (Connection conn = connect()) {
            if (conn != null) {
                createTable();
            }
        } catch (SQLException e) {
            System.out.println("Error inicializando la base de datos: " + e.getMessage());
        }
    }

    // Metodo para conectar a la base de datos
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // Metodo para crear la tabla Usuario
    private void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS Usuario (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    DNI TEXT NOT NULL UNIQUE,
                    nomina REAL NOT NULL,
                    saldo REAL NOT NULL
                );
                """;
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            if(stmt.execute()) System.out.println("Tabla Usuario creada.");

        } catch (SQLException e) {
            System.out.println("Error creando la tabla Usuario: " + e.getMessage());
        }
    }

    // Metodo para insertar un nuevo usuario
    public void insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario (nombre, DNI, nomina, saldo) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getDNI());
            stmt.setDouble(3, usuario.getNomina());
            stmt.setDouble(4, usuario.getSaldo());
            stmt.executeUpdate();
            System.out.println("Usuario insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error insertando el usuario: " + e.getMessage());
        }
    }

    public void modificarSaldo(String DNI,double saldo) {
        String sql = "UPDATE Usuario SET saldo = ? WHERE DNI = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, saldo);
            stmt.setString(2, DNI);

            if (stmt.executeUpdate() > 0) System.out.println("Saldo modificado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error eliminando el usuario: " + e.getMessage());
        }
    }

    public boolean usuarioExiste(String DNI) {

        String sql = "SELECT * FROM Usuario WHERE DNI = ?";

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, DNI);
            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error eliminando el usuario: " + e.getMessage());
        }
        return false;
    }

    public Usuario getUsuario(String DNI) {

        String sql = "SELECT * FROM Usuario WHERE DNI = ?";

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, DNI);
            ResultSet rs = stmt.executeQuery();

            return new Usuario(rs.getString("nombre"),rs.getString("DNI"),rs.getDouble("saldo"),rs.getDouble("nomina"));

        } catch (SQLException e) {
            System.out.println("Error eliminando el usuario: " + e.getMessage());
        }
        return null;
    }

}

