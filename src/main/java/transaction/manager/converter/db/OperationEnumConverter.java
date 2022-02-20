package transaction.manager.converter.db;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import transaction.manager.domain.enums.OperationEnum;

@Converter
public class OperationEnumConverter implements AttributeConverter<OperationEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final OperationEnum pickType) {
        return pickType.getCode();
    }

    @Override
    public OperationEnum convertToEntityAttribute(final Integer dbData) {
        return OperationEnum.of(dbData);
    }

}
