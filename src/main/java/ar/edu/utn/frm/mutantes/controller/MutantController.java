package ar.edu.utn.frm.mutantes.controller;

import ar.edu.utn.frm.mutantes.dto.DnaRequest;
import ar.edu.utn.frm.mutantes.dto.StatsResponse;
import ar.edu.utn.frm.mutantes.service.MutantService;
import ar.edu.utn.frm.mutantes.service.StatsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class MutantController {

    private final MutantService mutantService;
    private final StatsService statsService;

    @PostMapping("mutant")
    public ResponseEntity<?> isMutant(@Valid @RequestBody DnaRequest request) {

        boolean esMutante = mutantService.esMutante(request.getDna());

        if (esMutante) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @GetMapping("stats")
    public ResponseEntity<StatsResponse> stats() {
        return ResponseEntity.ok(statsService.getStats());
    }
}
