package com.api.envios_api_spring_boot;

import com.api.envios_api_spring_boot.dto.EnvioDTO;
import com.api.envios_api_spring_boot.models.Envio;
import com.api.envios_api_spring_boot.models.EstadoEnvio;
import com.api.envios_api_spring_boot.service.EnviosService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnviosTest {

    @Test
    public void testToDto() {
        // Preparar datos de prueba
        Envio envio = new Envio();
        envio.setId(1);
        envio.setIdVenta(101);
        envio.setDireccionEnvio("Calle 123");

        EstadoEnvio estado = new EstadoEnvio();
        estado.setId(1);
        estado.setNombre("Enviado");
        envio.setEstadoEnvio(estado);

        
        EnviosService enviosService = new EnviosService(); 

        
        EnvioDTO dto = enviosService.toDto(envio);

        
        assertNotNull(dto);
        assertEquals(1, dto.getId());
        assertEquals(101, dto.getIdVenta());
        assertEquals("Calle 123", dto.getDireccionEnvio());
        assertEquals(1, dto.getIdEstadoEnvio());
    }
}