package com.api.envios_api_spring_boot;

import com.api.envios_api_spring_boot.dto.EnvioDTO;
import com.api.envios_api_spring_boot.models.Envio;
import com.api.envios_api_spring_boot.models.EstadoEnvio;
import com.api.envios_api_spring_boot.repository.EnviosRepository;
import com.api.envios_api_spring_boot.repository.EstadoEnvioRepository;
import com.api.envios_api_spring_boot.service.EnviosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class EnviosServiceTest {

    @Mock
    private EnviosRepository enviosRepository;

    @Mock
    private EstadoEnvioRepository estadoEnvioRepository;

    @InjectMocks
    private EnviosService enviosService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testActualizarEstadoEnvio_Exitoso() {
        Integer envioId = 1;
        Integer nuevoEstadoId = 1;
        LocalDateTime fecha = LocalDateTime.now();
        Envio envio = new Envio();
        envio.setId(envioId);
        envio.setIdVenta(101);
        envio.setDireccionEnvio("Calle 123");
        envio.setFechaEnvio(fecha);
        envio.setFechaEntrega(fecha.plusDays(2));

        EstadoEnvio estado = new EstadoEnvio();
        estado.setId(nuevoEstadoId);
        estado.setNombre("Enviado");

        when(enviosRepository.findById(envioId)).thenReturn(Optional.of(envio));
        when(estadoEnvioRepository.findById(nuevoEstadoId)).thenReturn(Optional.of(estado));
        when(enviosRepository.save(envio)).thenReturn(envio);

        EnvioDTO resultado = enviosService.actualizarEstadoEnvio(envioId, nuevoEstadoId);

        assertNotNull(resultado);
        assertEquals(envioId, resultado.getId());
        assertEquals(nuevoEstadoId, resultado.getIdEstadoEnvio());
        assertEquals(fecha, resultado.getFechaEnvio());
        assertEquals(fecha.plusDays(2), resultado.getFechaEntrega());
        verify(enviosRepository, times(1)).findById(envioId);
        verify(estadoEnvioRepository, times(1)).findById(nuevoEstadoId);
        verify(enviosRepository, times(1)).save(envio);
    }

    @Test
    public void testActualizarEstadoEnvio_EnvioNoEncontrado() {
        Integer envioId = 999;
        when(enviosRepository.findById(envioId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> enviosService.actualizarEstadoEnvio(envioId, 1));
        verify(enviosRepository, times(1)).findById(envioId);
        verify(estadoEnvioRepository, never()).findById(anyInt());
        verify(enviosRepository, never()).save(any());
    }
}