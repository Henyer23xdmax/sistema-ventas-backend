package com.sisetma.backend.controller;

import com.sisetma.backend.model.UnidadMedida;
import com.sisetma.backend.repository.UnidadMedidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unidades-medida")
@RequiredArgsConstructor
public class UnidadMedidaController {

    private final UnidadMedidaRepository unidadMedidaRepository;

    @GetMapping
    public List<UnidadMedida> listarUnidadesMedida() {
        return unidadMedidaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadMedida> obtenerUnidadMedida(@PathVariable Integer id) {
        return unidadMedidaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UnidadMedida> crearUnidadMedida(@RequestBody UnidadMedida unidadMedida) {
        UnidadMedida nuevaUnidad = unidadMedidaRepository.save(unidadMedida);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaUnidad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadMedida> actualizarUnidadMedida(@PathVariable Integer id,
            @RequestBody UnidadMedida unidadMedida) {
        return unidadMedidaRepository.findById(id)
                .map(unidadExistente -> {
                    unidadExistente.setNombre(unidadMedida.getNombre());
                    unidadExistente.setAbreviatura(unidadMedida.getAbreviatura());
                    UnidadMedida unidadActualizada = unidadMedidaRepository.save(unidadExistente);
                    return ResponseEntity.ok(unidadActualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUnidadMedida(@PathVariable Integer id) {
        if (unidadMedidaRepository.existsById(id)) {
            unidadMedidaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
