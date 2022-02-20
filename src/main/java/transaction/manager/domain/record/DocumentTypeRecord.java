package transaction.manager.domain.record;

import java.util.UUID;
import javax.validation.constraints.Max;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.core.annotation.Introspected;

@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public record DocumentTypeRecord(
        UUID id,
        @Max(999) Integer code,
        String description
) {
}
