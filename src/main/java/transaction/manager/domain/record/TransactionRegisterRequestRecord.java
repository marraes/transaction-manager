package transaction.manager.domain.record;

import java.math.BigDecimal;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;

@Introspected
@Schema(name = "TransactionRegisterRequest", description = "Object that contains the data to create a new transaction")
public record TransactionRegisterRequestRecord(
        @Schema(name = "transactionTypeId", description = "Transaction type identifier")
        @Nonnull UUID transactionTypeId,

        @Nonnull @Min(0) BigDecimal amount,

        @Schema(name = "installments", description = "Qty of installments for transaction")
        @Nullable @Min(1) @Max(99) Integer installments
) {
}
