package com.api.envios_api_spring_boot.repository;

import com.api.envios_api_spring_boot.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;



public interface EnviosRepository extends JpaRepository<Envio, Long> {
    
}
