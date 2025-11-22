package com.sisetma.backend.repository;

import com.sisetma.backend.model.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComprobanteRepository extends JpaRepository<Comprobante, Integer> {
    // Para obtener el último número de una serie y autoincrementar
    Comprobante findTopBySerieOrderByNumeroDesc(String serie);
}
