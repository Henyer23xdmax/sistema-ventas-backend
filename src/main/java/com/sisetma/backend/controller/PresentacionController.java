package com.sisetma.backend.controller;

import com.sisetma.backend.model.Presentacion;
import com.sisetma.backend.repository.PresentacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/presentaciones")
@RequiredArgsConstructor
public class PresentacionController {

    private final PresentacionRepository presentacionRepository;

    @GetMapping
    public List<Presentacion> listarPresentaciones() {
        return presentacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Presentacion> obtenerPresentacion(@PathVariable Integer id) {
        return presentacionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Presentacion> crearPresentacion(@RequestBody Presentacion presentacion) {
        Presentacion nuevaPresentacion = presentacionRepository.save(presentacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPresentacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Presentacion> actualizarPresentacion(@PathVariable Integer id,
            @RequestBody Presentacion presentacion) {
        return presentacionRepository.findById(id)
                .map(presentacionExistente -> {
                    presentacionExistente.setNombre(presentacion.getNombre());
                    presentacionExistente.setDescripcion(presentacion.getDescripcion());
                    Presentacion presentacionActualizada = presentacionRepository.save(presentacionExistente);
                    return ResponseEntity.ok(presentacionActualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPresentacion(@PathVariable Integer id) {
        if (presentacionRepository.existsById(id)) {
            presentacionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
