package com.api.envios_api_spring_boot.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="envio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idVenta;
    private String direccionEnvio;
    private String estadoEnvio;
    private LocalDate fechaEnvio;
    private LocalDate fechaEntrega;

    @ManyToOne
    @JoinColumn(name = "id_estado_envio")
    private EstadoEnvio estadoEnvioEntity;
}
