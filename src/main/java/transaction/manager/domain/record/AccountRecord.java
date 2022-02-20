package transaction.manager.domain.record;

import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.core.annotation.Introspected;

@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public record AccountRecord(
        @Nullable UUID id,
        @Nonnull String accountNumber
) {
}
