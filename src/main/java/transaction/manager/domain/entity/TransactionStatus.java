package transaction.manager.domain.entity;

import java.io.Serial;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import transaction.manager.domain.entity.generic.AbstractEntityDomain;

@Getter
@Setter
@Entity
@ToString(callSuper = true)
@SuppressWarnings("java:S2160")
@Table(name = "TRANSACTION_STATUS")
public class TransactionStatus extends AbstractEntityDomain {

    @Serial
    private static final long serialVersionUID = -587690628540207038L;

    @Id
    @GeneratedValue
    @Column(name = "IDT_TRANSACTION_STATUS")
    private UUID id;

    @Nonnull
    @Max(999)
    @Column(name = "COD_TRANSACTION_STATUS")
    private Integer code;

    @Nonnull
    @Column(name = "DES_TRANSACTION_STATUS")
    private String description;

    @Override
    protected int hashCodeImpl() {
        return Objects.hash(code, description);
    }
}
