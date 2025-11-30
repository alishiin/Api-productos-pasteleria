package com.example.Api.productos.pasteleria.controller;

import com.example.Api.productos.pasteleria.model.Mensaje;
import com.example.Api.productos.pasteleria.repository.MensajeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacto")
@CrossOrigin(origins = "*")

@Tag(
        name = "Contacto",
        description = "Endpoints para recibir mensajes de clientes."
)

public class MensajeController {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Operation(
            summary = "Enviar mensaje de contacto",
            description = "Recibe un mensaje desde el formulario del sitio web y lo almacena en la base de datos."
    )

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mensaje enviado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })

    @PostMapping
    public ResponseEntity<Mensaje> enviarMensaje(@RequestBody Mensaje mensaje) {
        Mensaje nuevoMensaje = mensajeRepository.save(mensaje);
        return ResponseEntity.status(201).body(nuevoMensaje);
    }


    @Operation(
            summary = "Listar mensajes",
            description = "Obtiene una lista con todos los mensajes enviados desde el formulario de contacto."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de mensajes obtenida correctamente")
    })

    @GetMapping
    public ResponseEntity<?> listarMensajes() {
        return ResponseEntity.ok(mensajeRepository.findAll());
    }
}
