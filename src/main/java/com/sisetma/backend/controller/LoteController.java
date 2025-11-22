package com.sisetma.backend.controller;

import com.sisetma.backend.model.Lote;
import com.sisetma.backend.repository.LoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lotes")
@RequiredArgsConstructor
public class LoteController {

    private final LoteRepository loteRepository;

    @GetMapping
    public List<Lote> listarLotes() {
        return loteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lote> obtenerLote(@PathVariable Integer id) {
        return loteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Lote> crearLote(@RequestBody Lote lote) {
        Lote nuevoLote = loteRepository.save(lote);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lote> actualizarLote(@PathVariable Integer id, @RequestBody Lote lote) {
        return loteRepository.findById(id)
                .map(loteExistente -> {
                    loteExistente.setProducto(lote.getProducto());
                    loteExistente.setCodigoLote(lote.getCodigoLote());
                    loteExistente.setPrecioCompra(lote.getPrecioCompra());
                    loteExistente.setPrecioVenta(lote.getPrecioVenta());
                    loteExistente.setFechaVencimiento(lote.getFechaVencimiento());
                    loteExistente.setCantidad(lote.getCantidad());
                    Lote loteActualizado = loteRepository.save(loteExistente);
                    return ResponseEntity.ok(loteActualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLote(@PathVariable Integer id) {
        if (loteRepository.existsById(id)) {
            loteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
