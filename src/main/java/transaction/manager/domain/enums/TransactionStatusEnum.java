package transaction.manager.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionStatusEnum {

    REGISTERED(1),
    PERFORMED(2),
    PENDING_CONFIRMATION(3),
    CONFIRMED(4),
    DENIED(5),
    CANCELED(6),
    UNKNOWN(null),
    ;

    private final Integer dbCode;

}
