package com.api.envios_api_spring_boot.repository;

import com.api.envios_api_spring_boot.models.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnviosRepository extends JpaRepository<Envio, Integer> {
}