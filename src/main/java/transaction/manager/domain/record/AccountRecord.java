package transaction.manager.domain.record;

import java.math.BigDecimal;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;

@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Account", description = "Representation for an account")
public record AccountRecord(
        @Schema(name = "id", description = "Account identifier")
        @Nullable UUID id,

        @Schema(name = "accountNumber", description = "Uniquer number that identify an account")
        @Nonnull String accountNumber,

        @Nonnull @Min(0) BigDecimal balance
) {
}
