package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.TipoCambio;

public interface TipoCambioRepository extends JpaRepository<TipoCambio, Long> {
}
