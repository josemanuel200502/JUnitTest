package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {


    @Test
    void testComprobarDniValido() {
        assertTrue(Usuario.comprobarDni("12345678Z"), "El DNI debería ser válido");
    }

    @Test
    void testComprobarDniInvalido() {
        assertFalse(Usuario.comprobarDni("1234A678Z"), "El DNI no debería ser válido");
        assertFalse(Usuario.comprobarDni("12345678X"), "El DNI no debería ser válido");
    }
}