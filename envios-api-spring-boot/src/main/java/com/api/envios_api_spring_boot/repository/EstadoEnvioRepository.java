package com.api.envios_api_spring_boot.repository;

import com.api.envios_api_spring_boot.models.EstadoEnvio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoEnvioRepository extends JpaRepository<EstadoEnvio, Integer> {
}