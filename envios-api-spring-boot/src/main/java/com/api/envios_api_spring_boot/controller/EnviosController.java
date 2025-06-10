package com.api.envios_api_spring_boot.controller;

import com.api.envios_api_spring_boot.dto.EnvioDTO;
import com.api.envios_api_spring_boot.dto.EstadoDTO;
import com.api.envios_api_spring_boot.service.EnviosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/envios")
public class EnviosController {

    @Autowired
    private EnviosService enviosService;

    @PostMapping
    public ResponseEntity<EnvioDTO> crearEnvio(@RequestBody EnvioDTO dto) {
        return ResponseEntity.ok(enviosService.crearEnvio(dto));
    }

    @GetMapping
    public ResponseEntity<List<EnvioDTO>> listarEnvios() {
        return ResponseEntity.ok(enviosService.listarEnvios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnvioDTO> obtenerEnvio(@PathVariable Integer id) {
        return ResponseEntity.ok(enviosService.buscarEnvio(id));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<EnvioDTO> actualizarEstadoEnvio(@PathVariable Integer id, @RequestBody EstadoDTO estadoDTO) {
        EnvioDTO envioActualizado = enviosService.actualizarEstadoEnvio(id, estadoDTO.getIdEstadoEnvio());
        return ResponseEntity.ok(envioActualizado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnvioDTO> actualizarEnvio(@PathVariable Integer id, @RequestBody EnvioDTO dto) {
        return ResponseEntity.ok(enviosService.actualizarEnvio(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEnvio(@PathVariable Integer id) {
        enviosService.eliminarEnvio(id);
        return ResponseEntity.noContent().build();
    }
}