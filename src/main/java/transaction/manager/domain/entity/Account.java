package transaction.manager.domain.entity;

import java.io.Serial;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "ACCOUNT")
@ToString(callSuper = true)
@SuppressWarnings("java:S2160")
public class Account extends AbstractEntityDomain {

    @Serial
    private static final long serialVersionUID = -9058222420927660552L;

    @Id
    @GeneratedValue
    @Column(name = "IDT_ACCOUNT")
    private UUID id;

    @Nonnull
    @Column(name = "NUM_ACCOUNT")
    private String accountNumber;

    @Nonnull
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "IDT_CUSTOMER")
    private Customer customer;

    @Override
    protected int hashCodeImpl() {
        return Objects.hashCode(accountNumber);
    }

}
