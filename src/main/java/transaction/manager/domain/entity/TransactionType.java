package transaction.manager.domain.entity;

import java.io.Serial;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import transaction.manager.converter.db.OperationEnumConverter;
import transaction.manager.domain.entity.generic.AbstractEntityDomain;
import transaction.manager.domain.enums.OperationEnum;

@Getter
@Setter
@Entity
@ToString(callSuper = true)
@SuppressWarnings("java:S2160")
@Table(name = "TRANSACTION_TYPE")
public class TransactionType extends AbstractEntityDomain {

    @Serial
    private static final long serialVersionUID = 2537145683425862811L;

    @Id
    @GeneratedValue
    @Column(name = "IDT_TRANSACTION_TYPE")
    private UUID id;

    @Nonnull
    @Max(999)
    @Column(name = "COD_TRANSACTION_TYPE")
    private Integer code;

    @Nonnull
    @Column(name = "DES_TRANSACTION_TYPE")
    private String description;

    @Nonnull
    @Column(name = "IND_OPERATION")
    @Convert(converter = OperationEnumConverter.class)
    private OperationEnum operation;

    @Override
    protected int hashCodeImpl() {
        return Objects.hash(code, description);
    }

}
