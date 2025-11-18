package com.ucp.gestor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class RF02EnvioCorreosTest {

    private GestorEmails gestor;
    private Contacto c1, c2, c3;

    @BeforeEach
    void setUp() {
        gestor = new GestorEmails();
        c1 = gestor.crearContacto("A", "a@demo.com");
        c2 = gestor.crearContacto("B", "b@demo.com");
        c3 = gestor.crearContacto("C", "c@demo.com");
    }

    @Test
    void enviarCorreo_pasaDeBorradorAEnviadosYEntrada() {
        Email e = gestor.crearEmail(c1, "Asunto", "Contenido", Arrays.asList(c2, c3));

        gestor.enviar(e);

        assertFalse(e.isBorrador());
        assertTrue(gestor.getTodosEnBandeja(BandejaType.ENVIADOS).contains(e));
        assertTrue(gestor.getTodosEnBandeja(BandejaType.ENTRADA).contains(e));
    }

    @Test
    void enviarCorreo_actualizaFechaYLoMarcaComoNoLeido() {
        Email e = gestor.crearEmail(c1, "Asunto", "Contenido", Arrays.asList(c2));

        gestor.enviar(e);

        assertNotNull(e.getFecha());
        assertFalse(e.isLeido());
    }
}
