package transaction.manager.resolver;

import java.math.BigDecimal;
import javax.annotation.Nonnull;

import jakarta.inject.Singleton;
import transaction.manager.domain.enums.OperationEnum;

@Singleton
public class TransactionAmountResolver {

    @Nonnull
    public BigDecimal resolveTransactionAmount(@Nonnull final OperationEnum operation, @Nonnull final BigDecimal currentAmount) {
        final BigDecimal absAmount = currentAmount.abs();
        return operation == OperationEnum.DEBIT ? absAmount.negate() : absAmount;
    }

}
