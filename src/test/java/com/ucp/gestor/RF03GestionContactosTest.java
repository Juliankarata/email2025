package com.ucp.gestor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class RF03GestionContactosTest {

    private GestorEmails gestor;

    @BeforeEach
    void setUp() {
        gestor = new GestorEmails();
        gestor.crearContacto("A", "a@demo.com");
    }

    @Test
    void crearEditarEliminarContacto_flujoCompleto() {
        Contacto nuevo = gestor.crearContacto("Nuevo", "nuevo@demo.com");
        assertTrue(gestor.listarContactos().contains(nuevo));

        boolean editado = gestor.editarContacto("nuevo@demo.com", "Editado", "editado@demo.com");
        assertTrue(editado);

        Optional<Contacto> buscado = gestor.buscarContactoPorEmail("editado@demo.com");
        assertTrue(buscado.isPresent());
        assertEquals("Editado", buscado.get().getNombre());

        boolean eliminado = gestor.eliminarContacto("editado@demo.com");
        assertTrue(eliminado);
    }

    @Test
    void editarOEliminarContactoInexistente_devuelveFalse() {
        boolean editado = gestor.editarContacto("no@existe.com", "X", "Y");
        boolean eliminado = gestor.eliminarContacto("no@existe.com");

        assertFalse(editado);
        assertFalse(eliminado);
    }

    @Test
    void equalsHashCodeYToStringDeContacto_funcionanPorEmail() {
        Contacto c1 = new Contacto("Nombre1", "demo@correo.com");
        Contacto c2 = new Contacto("Nombre2", "Demo@Correo.com");

        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
        assertTrue(c1.toString().contains("demo@correo.com") || c1.toString().contains("Demo@Correo.com"));
    }
}
