package com.sisetma.backend.controller;

import com.sisetma.backend.model.TipoDocumento;
import com.sisetma.backend.repository.TipoDocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-documento")
@RequiredArgsConstructor
public class TipoDocumentoController {

    private final TipoDocumentoRepository tipoDocumentoRepository;

    @GetMapping
    public List<TipoDocumento> listarTiposDocumento() {
        return tipoDocumentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDocumento> obtenerTipoDocumento(@PathVariable Integer id) {
        return tipoDocumentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoDocumento> crearTipoDocumento(@RequestBody TipoDocumento tipoDocumento) {
        TipoDocumento nuevoTipoDocumento = tipoDocumentoRepository.save(tipoDocumento);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTipoDocumento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoDocumento> actualizarTipoDocumento(@PathVariable Integer id,
            @RequestBody TipoDocumento tipoDocumento) {
        return tipoDocumentoRepository.findById(id)
                .map(tipoDocumentoExistente -> {
                    tipoDocumentoExistente.setNombre(tipoDocumento.getNombre());
                    TipoDocumento tipoDocumentoActualizado = tipoDocumentoRepository.save(tipoDocumentoExistente);
                    return ResponseEntity.ok(tipoDocumentoActualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoDocumento(@PathVariable Integer id) {
        if (tipoDocumentoRepository.existsById(id)) {
            tipoDocumentoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
