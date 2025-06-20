package com.api.envios_api_spring_boot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "envio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer idVenta;
    private String direccionEnvio;

    @ManyToOne
    @JoinColumn(name = "id_estado_envio")
    private EstadoEnvio estadoEnvio;

    private LocalDate fechaEnvio;
    private LocalDate fechaEntrega;
}