package com.sisetma.backend.controller;

import com.sisetma.backend.model.Cliente;
import com.sisetma.backend.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable Integer id) {
        return clienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        // Set default idTipoCliente if not provided
        if (cliente.getIdTipoCliente() == null) {
            cliente.setIdTipoCliente(1); // Default to tipo cliente 1
        }
        // Set default idTipoDocumento if not provided
        if (cliente.getIdTipoDocumento() == null) {
            cliente.setIdTipoDocumento(1); // Default to tipo documento 1 (DNI)
        }
        Cliente nuevoCliente = clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        return clienteRepository.findById(id)
                .map(clienteExistente -> {
                    clienteExistente.setNombre(cliente.getNombre());
                    clienteExistente.setApellidos(cliente.getApellidos());
                    clienteExistente.setNroDocumento(cliente.getNroDocumento());
                    clienteExistente.setDireccion(cliente.getDireccion());
                    clienteExistente.setCelular(cliente.getCelular());
                    clienteExistente.setEstado(cliente.getEstado());
                    if (cliente.getIdTipoCliente() != null) {
                        clienteExistente.setIdTipoCliente(cliente.getIdTipoCliente());
                    }
                    Cliente clienteActualizado = clienteRepository.save(clienteExistente);
                    return ResponseEntity.ok(clienteActualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Integer id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
