package com.sisetma.backend.controller;

import com.sisetma.backend.model.Subcategoria;
import com.sisetma.backend.repository.SubcategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategorias")
@RequiredArgsConstructor
public class SubcategoriaController {

    private final SubcategoriaRepository subcategoriaRepository;

    @GetMapping
    public List<Subcategoria> listarSubcategorias() {
        return subcategoriaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subcategoria> obtenerSubcategoria(@PathVariable Integer id) {
        return subcategoriaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Subcategoria> crearSubcategoria(@RequestBody Subcategoria subcategoria) {
        Subcategoria nuevaSubcategoria = subcategoriaRepository.save(subcategoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaSubcategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subcategoria> actualizarSubcategoria(@PathVariable Integer id,
            @RequestBody Subcategoria subcategoria) {
        return subcategoriaRepository.findById(id)
                .map(subcategoriaExistente -> {
                    subcategoriaExistente.setNombre(subcategoria.getNombre());
                    subcategoriaExistente.setIdCategoria(subcategoria.getIdCategoria());
                    Subcategoria subcategoriaActualizada = subcategoriaRepository.save(subcategoriaExistente);
                    return ResponseEntity.ok(subcategoriaActualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSubcategoria(@PathVariable Integer id) {
        if (subcategoriaRepository.existsById(id)) {
            subcategoriaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
