package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void gastar() {
        // Crear un nuevo usuario con saldo inicial
        Usuario usuario = new Usuario("Juan Pérez", "12345678B", 1500.0, 1500.0);

        // Gastar una cantidad dentro del saldo disponible
        usuario.gastar(500.0);
        assertEquals(1000.0, usuario.getSaldo(), "El saldo después de gastar debe ser 1000.0");

        // Intentar gastar una cantidad mayor al saldo disponible
        usuario.gastar(1500.0);
        assertEquals(1000.0, usuario.getSaldo(), "El saldo no debe cambiar si no hay suficiente dinero");
    }


    @Test
    void ingresar() {
        // Crear un nuevo usuario con saldo inicial
        Usuario usuario = new Usuario("Juan Pérez", "12345678B", 1000.0, 1500.0);

        // Ingresar una cantidad al saldo
        usuario.ingresar(500.0);
        assertEquals(1500.0, usuario.getSaldo(), "El saldo después de ingresar debe ser 1500.0");

        // Ingresar una cantidad negativa (no debería permitirlo, pero depende de tu implementación)
        usuario.ingresar(-100.0);
        assertEquals(1500.0, usuario.getSaldo(), "El saldo no debe cambiar si la cantidad ingresada es negativa");
    }
}

