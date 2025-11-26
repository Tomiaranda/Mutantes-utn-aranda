package ar.edu.utn.frm.mutantes.service;

import ar.edu.utn.frm.mutantes.dto.StatsResponse;
import ar.edu.utn.frm.mutantes.repository.DnaRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StatsServiceTest {

    private DnaRecordRepository repository;
    private StatsService statsService;

    @BeforeEach
    void setUp() {
        repository = mock(DnaRecordRepository.class);
        statsService = new StatsService(repository);
    }

    @Test
    void testZeroHumans() {
        when(repository.countByEsMutante(true)).thenReturn(5L);
        when(repository.countByEsMutante(false)).thenReturn(0L);

        StatsResponse r = statsService.getStats();

        assertEquals(5, r.getCountMutantDna());
        assertEquals(0, r.getCountHumanDna());
        assertEquals(5.0, r.getRatio());
    }

    @Test
    void testNoMutants() {
        when(repository.countByEsMutante(true)).thenReturn(0L);
        when(repository.countByEsMutante(false)).thenReturn(10L);

        StatsResponse r = statsService.getStats();

        assertEquals(0, r.getCountMutantDna());
        assertEquals(10, r.getCountHumanDna());
        assertEquals(0.0, r.getRatio());
    }

    @Test
    void testRatioCorrect() {
        when(repository.countByEsMutante(true)).thenReturn(4L);
        when(repository.countByEsMutante(false)).thenReturn(8L);

        assertEquals(0.5, statsService.getStats().getRatio());
    }

    @Test
    void testEqualMutantAndHuman() {
        when(repository.countByEsMutante(true)).thenReturn(6L);
        when(repository.countByEsMutante(false)).thenReturn(6L);

        assertEquals(1.0, statsService.getStats().getRatio());
    }

    @Test
    void testRepositoryCalls() {
        when(repository.countByEsMutante(true)).thenReturn(3L);
        when(repository.countByEsMutante(false)).thenReturn(9L);

        statsService.getStats();

        verify(repository).countByEsMutante(true);
        verify(repository).countByEsMutante(false);
    }
}
