package ar.edu.utn.frm.mutantes.service;

import ar.edu.utn.frm.mutantes.dto.StatsResponse;
import ar.edu.utn.frm.mutantes.repository.DnaRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final DnaRecordRepository repository;

    public StatsResponse getStats() {

        long mutants = repository.countByEsMutante(true);
        long humans = repository.countByEsMutante(false);

        double ratio;

        if (humans == 0) {
            ratio = mutants;
        } else {
            ratio = (double) mutants / humans;
        }

        return StatsResponse.builder()
                .countMutantDna(mutants)
                .countHumanDna(humans)
                .ratio(ratio)
                .build();
    }
}
