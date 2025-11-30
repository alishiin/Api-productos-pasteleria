package com.example.Api.productos.pasteleria.repository;

import com.example.Api.productos.pasteleria.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
}
