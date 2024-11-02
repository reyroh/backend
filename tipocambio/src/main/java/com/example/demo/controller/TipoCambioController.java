package com.example.demo.controller;

import com.example.demo.client.TipoCambioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TipoCambioController {

    @Autowired
    private TipoCambioClient tipoCambioClient;

    @GetMapping("/guardar")
    public String guardarTipoCambio() {
        return tipoCambioClient.obtenerYGuardarTipoCambio();
    }
}
