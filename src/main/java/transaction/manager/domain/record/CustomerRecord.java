package transaction.manager.domain.record;

import java.time.LocalDate;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;

@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Customer", description = "Object that represent a customer")
public record CustomerRecord(
        @Schema(name = "id", description = "Customer identifier")
        @Nullable UUID id,

        @Schema(name = "name", description = "Customer name")
        @Nonnull String name,

        @Schema(name = "birthday", description = "Customer birthday")
        @Nonnull @JsonSerialize(using = ToStringSerializer.class) LocalDate birthday,

        @Schema(name = "documentNumber", description = "Customer document number")
        @Nonnull String documentNumber,

        @Nonnull DocumentTypeRecord documentType
) {
}
