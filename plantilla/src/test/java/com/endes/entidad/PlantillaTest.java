package com.endes.entidad;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;


/**
 * Pruebas unitarias para la clase Plantilla.
 */
class PlantillaTest {
    private Plantilla plantilla;

    @BeforeEach
    @DisplayName("Inicialización de la plantilla de empleados")
    void setUp() {
        plantilla = new Plantilla();
    }

    /**
     * Prueba que verifica que no se puedan contratar empleados con el mismo DNI.
     */
    @Test
    @DisplayName("No permite contratar empleados con el mismo DNI")
    void testContratarEmpleado_Duplicado() {
        Empleado tecnico1 = new Tecnico("11111111H", "Alejandro", "Fernández", 1000.0, 1);
        Empleado tecnico2 = new Tecnico("11111111H", "Carlos", "Pérez", 1200.0, 2); // Mismo DNI que el primero

        // Se permite el primer contrato
        assertDoesNotThrow(() -> plantilla.contratarEmpleado(tecnico1));

        // Intentar contratar otro empleado con el mismo DNI debería lanzar una excepción
        Exception ex = assertThrows(IllegalArgumentException.class, () -> plantilla.contratarEmpleado(tecnico2));

        // Verificar el mensaje de la excepción
        assertEquals("El empleado con DNI 11111111H ya está contratado", ex.getMessage());
    }
    
    /**
     * Prueba que verifica que un empleado nulo no se pueda contratar.
     */
    @Test
    @DisplayName("No permite contratar un empleado nulo")
    void testContratarEmpleado_Nulo() {
        Empleado tecnicoNulo = null;

        // Intentar contratar un empleado nulo debería lanzar una excepción
        Exception ex = assertThrows(IllegalArgumentException.class, () -> plantilla.contratarEmpleado(tecnicoNulo));

        // Verificar el mensaje de la excepción
        assertEquals("No se puede contratar un empleado nulo", ex.getMessage());
    }
    
    /**
     * Prueba que verifica la búsqueda de empleados por nombre.
     */
    @Test
    @DisplayName("Buscar empleados por nombre o apellido")
    void testGetEmpleadosPorNombre() {
        Empleado tecnico1 = new Tecnico("11111111H", "Alejandro", "Fernández", 1000.0, 1);
        Empleado tecnico2 = new Tecnico("22222222H", "Carlos", "Pérez", 1200.0, 2);
        Empleado tecnico3 = new Tecnico("33333333H", "Luis", "González", 1100.0, 3);
        
        // Contratamos los empleados
        plantilla.contratarEmpleado(tecnico1);
        plantilla.contratarEmpleado(tecnico2);
        plantilla.contratarEmpleado(tecnico3);

        // Buscar empleados que contengan "Alejandro" en el nombre
        List<Empleado> empleadosAlejandro = plantilla.getEmpleadosPorNombre("Alejandro");

        // Verificar que se encontró el empleado "Alejandro"
        assertEquals(1, empleadosAlejandro.size());
        assertTrue(empleadosAlejandro.contains(tecnico1));

        // Buscar empleados que contengan "Fernández" en el apellido
        List<Empleado> empleadosFernandez = plantilla.getEmpleadosPorNombre("Fernández");

        // Verificar que se encontró el empleado "Fernández"
        assertEquals(1, empleadosFernandez.size());
        assertTrue(empleadosFernandez.contains(tecnico1));

        // Buscar empleados con un nombre que no existe
        List<Empleado> empleadosNoExistentes = plantilla.getEmpleadosPorNombre("NoExistente");

        // Verificar que no se encontraron empleados
        assertTrue(empleadosNoExistentes.isEmpty());
    }
    
}
