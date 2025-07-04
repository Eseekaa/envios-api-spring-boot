package com.api.envios_api_spring_boot.controller;

import com.api.envios_api_spring_boot.dto.EnvioDTO;
import com.api.envios_api_spring_boot.service.EnviosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/envios")
public class EnviosController {

    @Autowired
    private EnviosService enviosService;

    @PostMapping
    public ResponseEntity<EntityModel<EnvioDTO>> crearEnvio(@RequestBody EnvioDTO dto) {
        if (dto.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        EnvioDTO createdDto = enviosService.crearEnvio(dto);
        EntityModel<EnvioDTO> resource = EntityModel.of(createdDto);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnviosController.class).obtenerHATEOAS(createdDto.getId())).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnviosController.class).obtenerTodosHATEOAS()).withRel("todos"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnviosController.class).eliminar(createdDto.getId())).withRel("eliminar"));
        return ResponseEntity.ok(resource);
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<EnvioDTO>>> listarEnvios() {
        List<EnvioDTO> dtos = enviosService.listarEnvios();
        List<EntityModel<EnvioDTO>> resources = dtos.stream()
                .map(dto -> EntityModel.of(dto)
                        .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnviosController.class).obtenerHATEOAS(dto.getId())).withSelfRel()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/hateoas/{id}")
    public ResponseEntity<EntityModel<EnvioDTO>> obtenerHATEOAS(@PathVariable Integer id) {
        EnvioDTO dto = enviosService.buscarEnvio(id);
        EntityModel<EnvioDTO> resource = EntityModel.of(dto);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnviosController.class).obtenerHATEOAS(id)).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnviosController.class).obtenerTodosHATEOAS()).withRel("todos"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnviosController.class).eliminar(id)).withRel("eliminar"));
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/hateoas")
    public ResponseEntity<List<EntityModel<EnvioDTO>>> obtenerTodosHATEOAS() {
        List<EnvioDTO> lista = enviosService.listarEnvios();
        List<EntityModel<EnvioDTO>> resources = lista.stream()
                .map(dto -> EntityModel.of(dto)
                        .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnviosController.class).obtenerHATEOAS(dto.getId())).withSelfRel()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        enviosService.eliminarEnvio(id);
        return ResponseEntity.noContent().build();
    }

    // Otros métodos (buscar, actualizar) pueden añadirse si los necesitas
}