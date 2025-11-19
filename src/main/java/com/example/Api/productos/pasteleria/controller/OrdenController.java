package com.example.Api.productos.pasteleria.controller;

import com.example.Api.productos.pasteleria.model.Orden;
import com.example.Api.productos.pasteleria.repository.OrdenRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
@CrossOrigin(origins = "*")
@Tag(
        name = "Órdenes",
        description = "Endpoints para gestionar las órdenes realizadas por los clientes."
)
public class OrdenController {

    private final OrdenRepository ordenRepository;

    public OrdenController(OrdenRepository ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    @Operation(
            summary = "Crear una orden",
            description = "Registra una nueva orden con productos, cantidades y datos del cliente."
    )
    @ApiResponse(responseCode = "201", description = "Orden creada exitosamente")

    @PostMapping
    public ResponseEntity<Orden> crearOrden(@RequestBody Orden orden) {
        Orden nueva = ordenRepository.save(orden);
        return ResponseEntity.status(201).body(nueva);
    }

    @Operation(
            summary = "Obtener todas las órdenes",
            description = "Devuelve la lista completa de órdenes registradas en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    @GetMapping
    public List<Orden> obtenerOrdenes() {
        return ordenRepository.findAll();
    }
}
