package com.sisetma.backend.controller;

import com.sisetma.backend.model.TipoCliente;
import com.sisetma.backend.repository.TipoClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-cliente")
@RequiredArgsConstructor
public class TipoClienteController {

    private final TipoClienteRepository tipoClienteRepository;

    @GetMapping
    public List<TipoCliente> listarTiposCliente() {
        return tipoClienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoCliente> obtenerTipoCliente(@PathVariable Integer id) {
        return tipoClienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoCliente> crearTipoCliente(@RequestBody TipoCliente tipoCliente) {
        TipoCliente nuevoTipoCliente = tipoClienteRepository.save(tipoCliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTipoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoCliente> actualizarTipoCliente(@PathVariable Integer id,
            @RequestBody TipoCliente tipoCliente) {
        return tipoClienteRepository.findById(id)
                .map(tipoClienteExistente -> {
                    tipoClienteExistente.setNombre(tipoCliente.getNombre());
                    tipoClienteExistente.setEstado(tipoCliente.getEstado());
                    TipoCliente tipoClienteActualizado = tipoClienteRepository.save(tipoClienteExistente);
                    return ResponseEntity.ok(tipoClienteActualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoCliente(@PathVariable Integer id) {
        if (tipoClienteRepository.existsById(id)) {
            tipoClienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
