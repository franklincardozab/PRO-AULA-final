package io.bootify.connect_stay_book_new.model;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import io.bootify.connect_stay_book_new.service.ReservasService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.springframework.web.servlet.HandlerMapping;


/**
 * Validate that the id value isn't taken yet.
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = ReservasPagoUnique.ReservasPagoUniqueValidator.class
)
public @interface ReservasPagoUnique {

    String message() default "{Exists.reservas.Pago}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class ReservasPagoUniqueValidator implements ConstraintValidator<ReservasPagoUnique, String> {

        private final ReservasService reservasService;
        private final HttpServletRequest request;

        public ReservasPagoUniqueValidator(final ReservasService reservasService,
                                        final HttpServletRequest request) {
            this.reservasService = reservasService;
            this.request = request;
        }

        @Override
        public boolean isValid(final String value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                return true;
            }
            @SuppressWarnings("unchecked")
            final Map<String, String> pathVariables =
                    (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            final String currentId = pathVariables.get("id");
            if (currentId != null && value.equals(reservasService.get(currentId).getPago())) {
                return true;
            }
            return !reservasService.pagoExists(value);
        }

    }

}
