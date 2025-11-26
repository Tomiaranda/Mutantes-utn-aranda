package ar.edu.utn.frm.mutantes.service;

import ar.edu.utn.frm.mutantes.entity.DnaRecord;
import ar.edu.utn.frm.mutantes.repository.DnaRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class MutantService {

    private final MutantDetector detector;
    private final DnaRecordRepository repository;

    public boolean esMutante(String[] dna) {

        String hash = sha256(dna);

        var existente = repository.findByCadena(hash);
        if (existente.isPresent()) {
            return existente.get().isEsMutante();
        }

        boolean resultado = detector.isMutant(dna);

        DnaRecord registro = DnaRecord.builder()
                .cadena(hash)
                .esMutante(resultado)
                .build();

        repository.save(registro);

        return resultado;
    }

    public long countMutants() {
        return repository.countByEsMutante(true);
    }

    public long countHumans() {
        return repository.countByEsMutante(false);
    }


    private String sha256(String[] dna) {
        try {
            String raw = String.join("", dna);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(raw.getBytes(StandardCharsets.UTF_8));

            StringBuilder hex = new StringBuilder();
            for (byte b : hashBytes) {
                String h = Integer.toHexString(0xff & b);
                if (h.length() == 1) hex.append('0');
                hex.append(h);
            }
            return hex.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generando hash SHA-256", e);
        }
    }
}
