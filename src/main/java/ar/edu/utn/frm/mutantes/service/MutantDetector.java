package ar.edu.utn.frm.mutantes.service;

import org.springframework.stereotype.Component;

@Component
public class MutantDetector {

    public boolean isMutant(String[] dna) {

        if (!validarDna(dna)) {
            return false;
        }

        int n = dna.length;
        int secuencias = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                char base = dna[i].charAt(j);


                if (j + 3 < n &&
                        base == dna[i].charAt(j + 1) &&
                        base == dna[i].charAt(j + 2) &&
                        base == dna[i].charAt(j + 3)) {
                    secuencias++;
                }


                if (i + 3 < n &&
                        base == dna[i + 1].charAt(j) &&
                        base == dna[i + 2].charAt(j) &&
                        base == dna[i + 3].charAt(j)) {
                    secuencias++;
                }


                if (i + 3 < n && j + 3 < n &&
                        base == dna[i + 1].charAt(j + 1) &&
                        base == dna[i + 2].charAt(j + 2) &&
                        base == dna[i + 3].charAt(j + 3)) {
                    secuencias++;
                }


                if (i + 3 < n && j - 3 >= 0 &&
                        base == dna[i + 1].charAt(j - 1) &&
                        base == dna[i + 2].charAt(j - 2) &&
                        base == dna[i + 3].charAt(j - 3)) {
                    secuencias++;
                }

                if (secuencias >= 2) return true;
            }
        }

        return false;
    }


    private boolean validarDna(String[] dna) {

        if (dna == null || dna.length == 0) {
            return false;
        }

        int n = dna.length;

        for (String fila : dna) {

            if (fila == null) return false;

            if (fila.length() != n) return false;


            if (!fila.matches("[ATCG]+")) {
                return false;
            }
        }

        return true;
    }
}
