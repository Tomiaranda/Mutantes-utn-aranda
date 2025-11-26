package ar.edu.utn.frm.mutantes.dto;

import ar.edu.utn.frm.mutantes.validation.ValidDnaSequence;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DnaRequest {

    @ValidDnaSequence
    private String[] dna;
}
