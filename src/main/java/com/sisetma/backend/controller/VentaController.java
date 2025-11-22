package com.sisetma.backend.controller;

import com.sisetma.backend.dto.VentaDTO;
import com.sisetma.backend.model.Comprobante;
import com.sisetma.backend.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    @PostMapping
    public ResponseEntity<?> registrarVenta(@RequestBody VentaDTO ventaDto) {
        try {
            Comprobante nuevaVenta = ventaService.guardarVenta(ventaDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al procesar la venta: " + e.getMessage());
        }
    }
}
