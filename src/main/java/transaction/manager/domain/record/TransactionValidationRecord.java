package transaction.manager.domain.record;

import java.math.BigDecimal;

import transaction.manager.domain.entity.Account;

public record TransactionValidationRecord (
        Account account,
        BigDecimal transactionAmount
){
}
