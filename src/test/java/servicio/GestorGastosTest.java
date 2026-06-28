package servicio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class GestorGastosTest {

    @Test
    public void pruebaSuma() {
        int esperado = 10;
        int obtenido = 5 + 5;

        assertEquals(esperado, obtenido);
    }
}