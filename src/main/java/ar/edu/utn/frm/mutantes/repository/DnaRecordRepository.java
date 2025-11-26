package ar.edu.utn.frm.mutantes.repository;

import ar.edu.utn.frm.mutantes.entity.DnaRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DnaRecordRepository extends JpaRepository<DnaRecord, Long> {

    Optional<DnaRecord> findByCadena(String cadena);

    long countByEsMutante(boolean esMutante);
}
