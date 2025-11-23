package com.sisetma.backend.controller;

import com.sisetma.backend.model.Proveedor;
import com.sisetma.backend.repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorRepository proveedorRepository;

    @GetMapping
    public List<Proveedor> listarProveedores() {
        return proveedorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerProveedor(@PathVariable Integer id) {
        return proveedorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Proveedor> crearProveedor(@RequestBody Proveedor proveedor) {
        Proveedor nuevoProveedor = proveedorRepository.save(proveedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProveedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable Integer id,
            @RequestBody Proveedor proveedor) {
        return proveedorRepository.findById(id)
                .map(proveedorExistente -> {
                    proveedorExistente.setRazonSocial(proveedor.getRazonSocial());
                    proveedorExistente.setNroDocumento(proveedor.getNroDocumento());
                    proveedorExistente.setIdTipoDocumento(proveedor.getIdTipoDocumento());
                    proveedorExistente.setDireccion(proveedor.getDireccion());
                    proveedorExistente.setTelefono(proveedor.getTelefono());
                    proveedorExistente.setCorreo(proveedor.getCorreo());
                    proveedorExistente.setEstado(proveedor.getEstado());
                    Proveedor proveedorActualizado = proveedorRepository.save(proveedorExistente);
                    return ResponseEntity.ok(proveedorActualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Integer id) {
        if (proveedorRepository.existsById(id)) {
            proveedorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
