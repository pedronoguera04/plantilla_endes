package com.endes.entidad;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComercialTest {
	
	private Comercial comercial;
	
	@BeforeEach
	void setUp() {
		comercial = new Comercial("12345678A", "Juan", "Pérez", 1500.0, 2000.0);
	}

	@Test
	void testGetVentas() {
		assertEquals(2000.0, comercial.getVentas());
	}

    @Test
    void testSetVentas() {
        comercial.setVentas(3000.0);
        assertEquals(3000.0, comercial.getVentas());
    }
    
    @Test
    void testSetVentasDebeLanzarExcepcionSiEsNegativo() {
        assertThrows(IllegalArgumentException.class, () -> comercial.setVentas(-500.0));
    }
    
    @Test
    void testCalcularExtra() {
        assertEquals(200.0, comercial.calcularExtra()); // 10% de 2000
    }
    
    @Test
    void testGetSueldo() {
        assertEquals(1700.0, comercial.getSueldo()); // 1500 + 200 (10% de 2000)
    }

}

