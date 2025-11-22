package com.sisetma.backend.controller;

import com.sisetma.backend.model.Comprobante;
import com.sisetma.backend.repository.ComprobanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comprobantes")
@RequiredArgsConstructor
public class ComprobanteController {

    private final ComprobanteRepository comprobanteRepository;

    @GetMapping
    public List<Comprobante> listarComprobantes() {
        return comprobanteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comprobante> obtenerComprobante(@PathVariable Integer id) {
        return comprobanteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
