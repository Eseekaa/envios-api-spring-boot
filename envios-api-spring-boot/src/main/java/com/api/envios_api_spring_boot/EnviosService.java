package com.api.envios_api_spring_boot;

import com.api.envios_api_spring_boot.model.Envio;
import com.api.envios_api_spring_boot.repository.EnviosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class EnviosService {
    @Autowired
    private EnviosRepository enviosRepository;
    
    public Envio creatEnvio(Envio envio) {
        return enviosRepository.save(envio);
    }

    public Envio getEnvioById(Long id){
        Optional<Envio> envio = enviosRepository.findById(id);
        return envio.orElse(null);
    }
    public Envio updateEstadoEnvio(Long id, String nuevoEstado) {
        Optional<Envio> envioOpt = enviosRepository.findById(id);
        if (envioOpt.isPresent()){
            Envio envio = envioOpt.get();
            envio.setEstadoEnvio(nuevoEstado);
            return enviosRepository.save(envio);
        }
    return null;
    }
}
