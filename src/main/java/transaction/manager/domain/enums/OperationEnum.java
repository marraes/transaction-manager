package transaction.manager.domain.enums;

import java.util.stream.Stream;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperationEnum {

    /**
     * Negative value in account
     */
    DEBIT(1),
    /**
     * Positive value in account
     */
    CREDIT(2),
    UNKNOWN(-1),
    ;

    private final Integer code;

    @Nonnull
    public static OperationEnum of(@Nullable Integer code) {
        return Stream.of(values())
                .filter(operation -> operation.code.equals(code))
                .findFirst()
                .orElse(UNKNOWN);
    }

}
