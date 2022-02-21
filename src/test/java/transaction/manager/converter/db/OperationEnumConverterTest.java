package transaction.manager.converter.db;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import transaction.manager.domain.enums.OperationEnum;

class OperationEnumConverterTest {

    private static Stream<Arguments> operationsToIntegerArgs() {
        return Stream.of(
                Arguments.of(OperationEnum.DEBIT, 1),
                Arguments.of(OperationEnum.CREDIT, 2),
                Arguments.of(OperationEnum.UNKNOWN, -1),
                Arguments.of(null, null)
        );
    }

    private static Stream<Arguments> integersToOperationArgs() {
        return Stream.of(
                Arguments.of(1, OperationEnum.DEBIT),
                Arguments.of(2, OperationEnum.CREDIT),
                Arguments.of(-1, OperationEnum.UNKNOWN),
                Arguments.of(null, OperationEnum.UNKNOWN)
        );
    }

    @ParameterizedTest
    @MethodSource("operationsToIntegerArgs")
    void givenOperation_whenConvertToDatabaseColumn_thenMustReturnAValidInteger(
            final OperationEnum operation,
            final Integer expectedOperationInteger
    ) {
        final OperationEnumConverter converter = new OperationEnumConverter();

        final Integer operationAsInteger = converter.convertToDatabaseColumn(operation);

        assertEquals(expectedOperationInteger, operationAsInteger);
    }

    @ParameterizedTest
    @MethodSource("integersToOperationArgs")
    void givenInteger_whenConvertToOperation_thenMustReturnAValidOperation(
            final Integer operationAsInteger,
            final OperationEnum expectedOperation
    ) {
        final OperationEnumConverter converter = new OperationEnumConverter();

        final OperationEnum operationConverted = converter.convertToEntityAttribute(operationAsInteger);

        assertEquals(expectedOperation, operationConverted);
    }

}
