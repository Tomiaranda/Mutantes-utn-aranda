package ar.edu.utn.frm.mutantes.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidDnaSequenceValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDnaSequence {
    String message() default "Secuencia de ADN inválida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
