package com.api.envios_api_spring_boot.dto;


import org.springframework.hateoas.RepresentationModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnvioDTO extends RepresentationModel<EnvioDTO> {
    private Integer id;
    private Integer idVenta;
    private String direccionEnvio;
    private Integer idEstadoEnvio;
    private LocalDateTime fechaEnvio;
    private LocalDateTime fechaEntrega;
}