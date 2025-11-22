package com.sisetma.backend.repository;

import com.sisetma.backend.model.Lote;
import com.sisetma.backend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Integer> {

    // Lógica FIFO: Buscar lotes con stock > 0 ordenados por vencimiento
    List<Lote> findByProductoAndCantidadGreaterThanOrderByFechaVencimientoAsc(Producto producto, int cantidadMinima);
}
