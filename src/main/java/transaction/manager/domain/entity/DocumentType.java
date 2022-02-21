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
@Table(name = "DOCUMENT_TYPE")
@SuppressWarnings("java:S2160")
public class DocumentType extends AbstractEntityDomain {

    @Serial
    private static final long serialVersionUID = 7872877412196075633L;

    @Id
    @GeneratedValue
    @Column(name = "IDT_DOCUMENT_TYPE")
    private UUID id;

    @Nonnull
    @Max(999)
    @Column(name = "COD_DOCUMENT_TYPE")
    private Integer code;

    @Nonnull
    @Column(name = "DES_DOCUMENT_TYPE")
    private String description;

    @Override
    protected int hashCodeImpl() {
        return Objects.hash(code, description);
    }

}
