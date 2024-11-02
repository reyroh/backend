package com.example.demo.controller;

import com.example.demo.client.TipoCambioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TipoCambioController {

    @Autowired
    private TipoCambioClient tipoCambioClient;

    @GetMapping("/guardarTipoCambioDiaString")
    public String guardarTipoCambioString() {
        return tipoCambioClient.obtenerYGuardarTipoCambioString();
    }
    
    @GetMapping("/guardarVariablesDisponibles")
    public String guardarVariablesDisponibles() {
        return tipoCambioClient.obtenerYGuardarVariablesDisponibles();
    }
    
    @GetMapping("/guardarVariables")
    public String guardarVariables() {
        return tipoCambioClient.obtenerYGuardarVariables();
    }
    
    @GetMapping("/guardarTipoCambioDia")
    public String guardarTipoCambio() {
        return tipoCambioClient.obtenerYGuardarTipoCambio();
    }
}
