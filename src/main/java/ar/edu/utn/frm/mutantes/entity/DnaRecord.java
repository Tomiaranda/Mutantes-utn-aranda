package ar.edu.utn.frm.mutantes.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dna_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DnaRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 64)
    private String cadena;

    @Column(nullable = false)
    private boolean esMutante;
}
