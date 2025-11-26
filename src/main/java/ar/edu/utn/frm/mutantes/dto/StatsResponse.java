package ar.edu.utn.frm.mutantes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatsResponse {

    private long countMutantDna;
    private long countHumanDna;
    private double ratio;
}
