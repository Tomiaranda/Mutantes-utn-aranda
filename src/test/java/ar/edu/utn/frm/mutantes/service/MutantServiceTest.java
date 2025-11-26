package ar.edu.utn.frm.mutantes.service;

import ar.edu.utn.frm.mutantes.entity.DnaRecord;
import ar.edu.utn.frm.mutantes.repository.DnaRecordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MutantServiceTest {

    private final MutantDetector detector = mock(MutantDetector.class);
    private final DnaRecordRepository repository = mock(DnaRecordRepository.class);
    private final MutantService service = new MutantService(detector, repository);

    @Test
    @DisplayName("Guarda en BD y retorna TRUE cuando es mutante")
    void testMutantIsSaved() {
        String[] dna = {"AAA", "TTT"};

        when(repository.findByCadena(any())).thenReturn(Optional.empty());
        when(detector.isMutant(dna)).thenReturn(true);
        when(repository.save(any())).thenAnswer(a -> a.getArgument(0));

        boolean result = service.esMutante(dna);
        assertTrue(result);

        ArgumentCaptor<DnaRecord> captor = ArgumentCaptor.forClass(DnaRecord.class);
        verify(repository).save(captor.capture());

        assertTrue(captor.getValue().isEsMutante());
    }

    @Test
    @DisplayName("Cuando existe en BD devuelve el valor cacheado")
    void testReturnsCachedValue() {
        DnaRecord record = DnaRecord.builder()
                .cadena("hash123")
                .esMutante(true)
                .build();

        when(repository.findByCadena(any())).thenReturn(Optional.of(record));

        boolean result = service.esMutante(new String[]{"AAA", "TTT"});

        assertTrue(result);
        verify(detector, never()).isMutant(any());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Cuenta mutantes correctamente")
    void testCountMutants() {
        when(repository.countByEsMutante(true)).thenReturn(7L);
        assertEquals(7L, service.countMutants());
    }

    @Test
    @DisplayName("Cuenta humanos correctamente")
    void testCountHumans() {
        when(repository.countByEsMutante(false)).thenReturn(10L);
        assertEquals(10L, service.countHumans());
    }
}
