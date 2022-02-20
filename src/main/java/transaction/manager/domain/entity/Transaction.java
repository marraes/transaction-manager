package transaction.manager.domain.entity;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import transaction.manager.domain.entity.generic.AbstractEntityDomain;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "TRANSACTION")
@SuppressWarnings("java:S2160")
public class Transaction extends AbstractEntityDomain {

    @Serial
    private static final long serialVersionUID = 2265670560627304814L;

    @Id
    @GeneratedValue
    @Column(name = "IDT_TRANSACTION")
    private UUID id;

    @Nonnull
    @Column(name = "DAT_EVENT")
    private Instant eventDate;

    @Nonnull
    @Column(name = "NUM_AMOUNT")
    private BigDecimal amount;

    @Max(99)
    @Nullable
    @Column(name = "NUM_INSTALLMENTS")
    private Integer installments;

    @Nonnull
    @ManyToOne
    @JoinColumn(name = "IDT_TRANSACTION_TYPE")
    private TransactionType type;

    @Nonnull
    @ManyToOne
    @JoinColumn(name = "IDT_TRANSACTION_STATUS")
    private TransactionStatus status;

    @Nonnull
    @ManyToOne
    @JoinColumn(name = "IDT_ACCOUNT")
    private Account account;

    @Override
    protected int hashCodeImpl() {
        return Objects.hash(type.getId(), eventDate);
    }

}
