package com.example.Api.productos.pasteleria.controller;

import com.example.Api.productos.pasteleria.model.Producto;
import com.example.Api.productos.pasteleria.repository.ProductoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
@Tag(name = "Productos",
description = "Endpoints para gestionar los productos de la pastelería: crear, actualizar, eliminar y obtener.")
public class ProductoController {

    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Operation(
            summary = "Crear un producto",
            description = "Permite registrar un nuevo producto con nombre, descripción, precio e imagen."
    )
    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente")
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto nuevo = productoRepository.save(producto);
        return ResponseEntity.status(201).body(nuevo);
    }

    @Operation(
            summary = "Actualizar un producto",
            description = "Actualiza un producto existente por ID con los nuevos datos enviados."
    )
    @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody Producto detalles) {
        Optional<Producto> productoExistente = productoRepository.findById(id);

        if (productoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Producto producto = productoExistente.get();
        producto.setNombre(detalles.getNombre());
        producto.setDescripcion(detalles.getDescripcion());
        producto.setPrecio(detalles.getPrecio());
        producto.setImagenUrl(detalles.getImagenUrl());

        productoRepository.save(producto);
        return ResponseEntity.ok(producto);
    }

    @Operation(
            summary = "Eliminar un producto",
            description = "Elimina un producto por su ID."
    )

    @ApiResponse(responseCode = "204", description = "Producto eliminado")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        if (!productoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Obtener todos los productos",
            description = "Devuelve la lista completa de productos registrados."
    )

    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        return ResponseEntity.ok(productoRepository.findAll());
    }

    @Operation(
            summary = "Obtener producto por ID",
            description = "Busca y devuelve un producto específico usando su ID."
    )
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Optional<Producto> producto = productoRepository.findById(id);

        if (producto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(producto.get());
    }
}
