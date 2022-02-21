package transaction.manager.resolver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import transaction.manager.domain.enums.OperationEnum;

class TransactionAmountResolverTest {

    private static Stream<Arguments> resolveAmountArgs() {
        return Stream.of(
                Arguments.of(OperationEnum.DEBIT, BigDecimal.valueOf(12.5), BigDecimal.valueOf(-12.5)),
                Arguments.of(OperationEnum.DEBIT, BigDecimal.valueOf(-12.5), BigDecimal.valueOf(-12.5)),
                Arguments.of(OperationEnum.CREDIT, BigDecimal.valueOf(12.5), BigDecimal.valueOf(12.5)),
                Arguments.of(OperationEnum.CREDIT, BigDecimal.valueOf(-12.5), BigDecimal.valueOf(12.5)),
                Arguments.of(OperationEnum.UNKNOWN, BigDecimal.valueOf(12.5), BigDecimal.valueOf(12.5)),
                Arguments.of(OperationEnum.UNKNOWN, BigDecimal.valueOf(-12.5), BigDecimal.valueOf(-12.5))
        );
    }

    @ParameterizedTest
    @MethodSource("resolveAmountArgs")
    void givenOperationAndAmount_whenResolveAmountValue_thenReturnAmountWithCorrectSign(
            final OperationEnum operation,
            final BigDecimal amountArg,
            final BigDecimal expectedAmount
    ) {
        final TransactionAmountResolver resolver = new TransactionAmountResolver();

        final BigDecimal amountResult = resolver.resolveTransactionAmount(operation, amountArg);

        assertEquals(expectedAmount, amountResult);
    }

}
