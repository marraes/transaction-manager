package transaction.manager.domain.record;

import java.time.LocalDate;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.micronaut.core.annotation.Introspected;

@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CustomerRecord(
        @Nullable UUID id,
        @Nonnull String name,
        @Nonnull @JsonSerialize(using = ToStringSerializer.class) LocalDate birthday,
        @Nonnull String documentNumber,
        @Nonnull DocumentTypeRecord documentType
) {
}
