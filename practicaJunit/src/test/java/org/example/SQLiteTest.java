package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;

class SQLiteTest {

    private static SQLite sqlite;

    // Setup que se ejecuta antes de cada prueba
    @BeforeEach
    void setUp() {
        sqlite = new SQLite(); // Inicializa la conexión y crea la tabla si no existe
    }

    // Test de insertarUsuario
    @Test
    void insertarUsuario() {
        // Prepara un usuario de prueba
        Usuario usuario = new Usuario("Juan Pérez", "12345678A", 1500.0, 100.0);

        // Inserta el usuario
        sqlite.insertarUsuario(usuario);

        // Verifica que el usuario exista
        assertTrue(sqlite.usuarioExiste("12345678A"));
    }

    // Test de modificarSaldo
    @Test
    void modificarSaldo() {
        // Prepara un usuario de prueba
        Usuario usuario = new Usuario("Ana Gómez", "87654321B", 2000.0, 500.0);
        sqlite.insertarUsuario(usuario); // Insertamos el usuario primero

        // Modifica el saldo
        sqlite.modificarSaldo("87654321B", 600.0);

        // Recupera el usuario y verifica el saldo
        Usuario usuarioModificado = sqlite.getUsuario("87654321B");
        assertNotNull(usuarioModificado);
        assertEquals(600.0, usuarioModificado.getSaldo(), "El saldo no fue modificado correctamente");
    }

    // Test de usuarioExiste
    @Test
    void usuarioExiste() {
        // Prepara un usuario de prueba
        Usuario usuario = new Usuario("Carlos Ruiz", "11223344C", 1800.0, 200.0);
        sqlite.insertarUsuario(usuario); // Insertamos el usuario primero

        // Verifica que el usuario existe
        assertTrue(sqlite.usuarioExiste("11223344C"));

        // Verifica que un usuario inexistente no exista
        assertFalse(sqlite.usuarioExiste("00000000X"));
    }

}