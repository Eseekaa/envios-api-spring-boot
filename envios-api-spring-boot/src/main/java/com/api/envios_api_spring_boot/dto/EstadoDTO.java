package com.api.envios_api_spring_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class  EstadoDTO extends RepresentationModel<EstadoDTO> {
    private Integer idEstadoEnvio; 
}