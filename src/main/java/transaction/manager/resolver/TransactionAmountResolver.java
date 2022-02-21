package transaction.manager.resolver;

import java.math.BigDecimal;
import javax.annotation.Nonnull;

import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import transaction.manager.domain.enums.OperationEnum;

@Slf4j
@Singleton
public class TransactionAmountResolver {

    @Nonnull
    public BigDecimal resolveTransactionAmount(@Nonnull final OperationEnum operation, @Nonnull final BigDecimal currentAmount) {
        log.info("resolveTransactionAmount step=start operation={}", operation);

        if (operation == OperationEnum.UNKNOWN) {
            return currentAmount;
        }

        final BigDecimal absAmount = currentAmount.abs();
        final BigDecimal amountResult = operation == OperationEnum.DEBIT ? absAmount.negate() : absAmount;

        log.info("resolveTransactionAmount step=end operation={}", operation);

        return amountResult;
    }

}
