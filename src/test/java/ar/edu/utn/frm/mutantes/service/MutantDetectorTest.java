package ar.edu.utn.frm.mutantes.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MutantDetectorTest {

    private MutantDetector detector;

    @BeforeEach
    void setUp() {
        detector = new MutantDetector();
    }

    @Test
    @DisplayName("Mutante con secuencia horizontal y diagonal")
    void testMutantHorizontalDiagonal() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        assertTrue(detector.isMutant(dna));
    }

    @Test
    @DisplayName("Mutante con secuencias verticales")
    void testMutantVertical() {
        String[] dna = {
                "AAAAGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CACCTA",
                "TCACTG"
        };
        assertTrue(detector.isMutant(dna));
    }

    @Test
    @DisplayName("Mutante con múltiples horizontales")
    void testMutantMultipleHorizontal() {
        String[] dna = {
                "TTTTGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        assertTrue(detector.isMutant(dna));
    }

    @Test
    @DisplayName("Mutante con diagonales ascendentes y descendentes")
    void testMutantBothDiagonals() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATTT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        assertTrue(detector.isMutant(dna));
    }

    @Test
    @DisplayName("Humano con una sola secuencia")
    void testHumanOneSequence() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATTT",
                "AGACGG",
                "GCGTCA",
                "TCACTG"
        };
        assertFalse(detector.isMutant(dna));
    }

    @Test
    @DisplayName("Humano sin secuencias")
    void testHumanNoSequences() {
        String[] dna = {
                "ATGC",
                "CAGT",
                "TTAT",
                "AGAC"
        };
        assertFalse(detector.isMutant(dna));
    }

    @Test
    @DisplayName("ADN nulo → false")
    void testNullDna() {
        assertFalse(detector.isMutant(null));
    }

    @Test
    @DisplayName("ADN vacío → false")
    void testEmptyDna() {
        assertFalse(detector.isMutant(new String[]{}));
    }

    @Test
    @DisplayName("Matriz no cuadrada → false")
    void testNonSquareMatrix() {
        assertFalse(detector.isMutant(new String[]{
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG"
        }));
    }

    @Test
    @DisplayName("Caracter inválido → false")
    void testInvalidCharacters() {
        assertFalse(detector.isMutant(new String[]{
                "ATGCGA",
                "CAGTXC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        }));
    }

    @Test
    @DisplayName("Fila nula → false")
    void testNullRow() {
        assertFalse(detector.isMutant(new String[]{
                "ATGCGA",
                null,
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        }));
    }


    @Test
    @DisplayName("Mutante 4x4")
    void test4x4Mutant() {
        assertTrue(detector.isMutant(new String[]{
                "AAAA",
                "CCCC",
                "TTAT",
                "AGAC"
        }));
    }

    @Test
    @DisplayName("Mutante 10x10")
    void test10x10Mutant() {
        assertTrue(detector.isMutant(new String[]{
                "ATGCGAATGC",
                "CAGTGCCAGT",
                "TTATGTTTAT",
                "AGAAGGATAA",
                "CCCCTACCCC",
                "TCACTGTCAC",
                "ATGCGAATGC",
                "CAGTGCCAGT",
                "TTATGTTTAT",
                "AGAAGGATAA"
        }));
    }

    @Test
    @DisplayName("Diagonal ascendente válida")
    void testAscendingDiagonal() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCGCTA",
                "TCGCTG"
        };
        assertDoesNotThrow(() -> detector.isMutant(dna));
    }

    @Test
    @DisplayName("Early termination")
    void testEarlyTermination() {
        String[] dna = {
                "AAAAGA",
                "AAAAGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };

        long start = System.nanoTime();
        boolean r = detector.isMutant(dna);
        long end = System.nanoTime();

        assertTrue(r);
        assertTrue((end - start) < 10_000_000);
    }

    @Test
    @DisplayName("Todas las bases iguales → mutante")
    void testAllSameBases() {
        String[] dna = {
                "AAAAAA",
                "AAAAAA",
                "AAAAAA",
                "AAAAAA",
                "AAAAAA",
                "AAAAAA"
        };
        assertTrue(detector.isMutant(dna));
    }
}
