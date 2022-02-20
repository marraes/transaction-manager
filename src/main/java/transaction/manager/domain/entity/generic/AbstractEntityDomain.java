package transaction.manager.domain.entity.generic;

import java.io.Serial;
import java.time.Instant;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.DateUpdated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Setter
@Getter
@ToString
@MappedSuperclass
public abstract class AbstractEntityDomain implements EntityDomain {

    @Serial
    private static final long serialVersionUID = 8605234499808167904L;

    @DateCreated
    @Column(name = "DAT_CREATED")
    private Instant createdAt;

    @DateUpdated
    @Column(name = "DAT_UPDATED")
    private Instant updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof EntityDomain that) ||
                Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }

        return this.getId() != null && that.getId() != null ?
                Objects.equals(this.getId(), that.getId()) :
                this.hashCode() == that.hashCode();
    }

    @Override
    public int hashCode() {
        return this.hashCodeImpl();
    }

    protected abstract int hashCodeImpl();

}
