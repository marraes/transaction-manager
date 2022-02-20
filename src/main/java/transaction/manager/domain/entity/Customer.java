package transaction.manager.domain.entity;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import transaction.manager.domain.entity.generic.AbstractEntityDomain;

@Getter
@Setter
@Entity
@Table(name = "CUSTOMER")
@ToString(callSuper = true)
@SuppressWarnings("java:S2160")
public class Customer extends AbstractEntityDomain {

    @Serial
    private static final long serialVersionUID = -7073567540530363464L;

    @Id
    @GeneratedValue
    @Column(name = "IDT_CUSTOMER")
    private UUID id;

    @Nonnull
    @ToString.Exclude
    @Column(name = "NAM_CUSTOMER")
    private String name;

    @Nonnull
    @ToString.Exclude
    @Column(name = "DAT_BIRTHDAY")
    private LocalDate birthday;

    @Nonnull
    @ToString.Exclude
    @Column(name = "NUM_DOCUMENT")
    private String documentNumber;

    @Nonnull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDT_DOCUMENT_TYPE")
    private DocumentType documentType;

    @Override
    protected int hashCodeImpl() {
        return Objects.hash(name, birthday, documentNumber);
    }

}
