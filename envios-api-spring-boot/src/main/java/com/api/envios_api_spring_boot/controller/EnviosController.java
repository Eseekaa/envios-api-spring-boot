package com.api.envios_api_spring_boot.controller;

import com.api.envios_api_spring_boot.model.Envio;
import com.api.envios_api_spring_boot.service.EnviosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/v1/envios")
public class EnviosController {

    @Autowired
    private EnviosService enviosService;

    @PostMapping
    public ResponseEntity<?> createEnvio(@RequestBody Envio envio) {
        Envio nuevoEnvio = enviosService.creatEnvio(envio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEnvio);    
    }
     @GetMapping("/{id}")
     public ResponseEntity<?> getEnvioById(@PathVariable Long id) {
        Envio envio =enviosService.getEnvioById(id);
        if (envio != null) {
            return ResponseEntity.ok(envio);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Envio no encontrado");
        }
     }
     @PutMapping("/{id}/estado")
     public ResponseEntity<?> updateEstadoEnvio(@PathVariable Long id, @RequestBody String nuevoEstado) {
        Envio updateEnvio = enviosService.updateEstadoEnvio(id, nuevoEstado);
        if (updateEnvio != null) {
            return ResponseEntity.ok(updateEnvio);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Envio no encontrado");
        }
     }
     
}
