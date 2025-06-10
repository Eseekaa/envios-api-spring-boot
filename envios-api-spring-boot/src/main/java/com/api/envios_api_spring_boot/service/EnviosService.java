package com.api.envios_api_spring_boot.service;

import com.api.envios_api_spring_boot.dto.EnvioDTO;
import com.api.envios_api_spring_boot.models.Envio;
import com.api.envios_api_spring_boot.models.EstadoEnvio;
import com.api.envios_api_spring_boot.repository.EnviosRepository;
import com.api.envios_api_spring_boot.repository.EstadoEnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnviosService {

    @Autowired
    private EnviosRepository enviosRepository;

    @Autowired
    private EstadoEnvioRepository estadoEnvioRepository;

    private Envio toEntity(EnvioDTO dto) {
        Envio envio = new Envio();
        envio.setId(dto.getId() != null ? dto.getId() : null); // Permitir ID nulo para creación
        envio.setIdVenta(dto.getIdVenta());
        envio.setDireccionEnvio(dto.getDireccionEnvio());

        if (dto.getIdEstadoEnvio() != null) {
            EstadoEnvio estado = estadoEnvioRepository.findById(dto.getIdEstadoEnvio())
                    .orElseThrow(() -> new RuntimeException("Estado de envío no encontrado con ID: " + dto.getIdEstadoEnvio()));
            envio.setEstadoEnvio(estado);
        } else {
            envio.setEstadoEnvio(null); // Si no se proporciona, se deja como nulo
        }

        envio.setFechaEnvio(dto.getFechaEnvio());
        envio.setFechaEntrega(dto.getFechaEntrega());
        return envio;
    }

    private EnvioDTO toDto(Envio envio) {
        return new EnvioDTO(
                envio.getId(),
                envio.getIdVenta(),
                envio.getDireccionEnvio(),
                envio.getEstadoEnvio() != null ? envio.getEstadoEnvio().getId() : null,
                envio.getFechaEnvio(),
                envio.getFechaEntrega()
        );
    }

    public EnvioDTO crearEnvio(EnvioDTO dto) {
        if (dto.getId() != null) {
            throw new RuntimeException("No se puede crear un envío con ID predefinido.");
        }
        Envio envio = toEntity(dto);
        Envio savedEnvio = enviosRepository.save(envio);
        return toDto(savedEnvio);
    }

    public List<EnvioDTO> listarEnvios() {
        return enviosRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public EnvioDTO buscarEnvio(Integer id) {
        Envio envio = enviosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Envío no encontrado con ID: " + id));
        return toDto(envio);
    }

    public EnvioDTO actualizarEnvio(Integer id, EnvioDTO dto) {
        Envio envioExistente = enviosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Envío no encontrado con ID: " + id));

        envioExistente.setIdVenta(dto.getIdVenta());
        envioExistente.setDireccionEnvio(dto.getDireccionEnvio());

        if (dto.getIdEstadoEnvio() != null) {
            EstadoEnvio estado = estadoEnvioRepository.findById(dto.getIdEstadoEnvio())
                    .orElseThrow(() -> new RuntimeException("Estado de envío no encontrado con ID: " + dto.getIdEstadoEnvio()));
            envioExistente.setEstadoEnvio(estado);
        }

        envioExistente.setFechaEnvio(dto.getFechaEnvio());
        envioExistente.setFechaEntrega(dto.getFechaEntrega());

        return toDto(enviosRepository.save(envioExistente));
    }

    public EnvioDTO actualizarEstadoEnvio(Integer id, Integer idEstadoEnvio) {
        Envio envioExistente = enviosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Envío no encontrado con ID: " + id));

        EstadoEnvio nuevoEstado = estadoEnvioRepository.findById(idEstadoEnvio)
                .orElseThrow(() -> new RuntimeException("Estado de envío no encontrado con ID: " + idEstadoEnvio));

        envioExistente.setEstadoEnvio(nuevoEstado);
        return toDto(enviosRepository.save(envioExistente));
    }

    public void eliminarEnvio(Integer id) {
        if (!enviosRepository.existsById(id)) {
            throw new RuntimeException("Envío no encontrado con ID: " + id);
        }
        enviosRepository.deleteById(id);
    }
}