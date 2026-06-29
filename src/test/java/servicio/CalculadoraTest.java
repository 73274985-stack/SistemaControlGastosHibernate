package servicio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {

    @Test
    public void pruebaSuma() {

        Calculadora calculadora = new Calculadora();

        assertEquals(8, calculadora.sumar(5, 3));

    }

}