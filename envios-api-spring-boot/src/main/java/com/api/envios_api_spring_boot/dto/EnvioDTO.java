package com.api.envios_api_spring_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EnvioDTO {
    private Integer id;
    private Integer idVenta;
    private String direccionEnvio;
    private Integer idEstadoEnvio;
    private LocalDate fechaEnvio;
    private LocalDate fechaEntrega;
}