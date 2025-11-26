package ar.edu.utn.frm.mutantes.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidDnaSequenceValidator implements ConstraintValidator<ValidDnaSequence, String[]> {

    @Override
    public boolean isValid(String[] dna, ConstraintValidatorContext context) {
        return isValidDna(dna);
    }

    public static boolean isValidDna(String[] dna) {

        if (dna == null || dna.length == 0 || dna.length >= 1000) {
            return false;
        }

        int n = dna.length;

        for (String row : dna) {

            if (row == null || row.length() != n) {
                return false;
            }

            // Solo A,T,C,G
            for (char c : row.toCharArray()) {
                if (c != 'A' && c != 'T' && c != 'C' && c != 'G') {
                    return false;
                }
            }
        }
        return true;
    }
}
