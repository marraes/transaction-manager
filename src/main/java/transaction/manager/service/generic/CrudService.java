package transaction.manager.service.generic;

import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.validation.Valid;

import io.micronaut.core.annotation.Indexed;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import transaction.manager.domain.entity.generic.EntityDomain;

@Indexed(CrudService.class)
public interface CrudService<E extends EntityDomain> {

    @Nonnull
    E save(@Valid @Nonnull E entity);

    @Nonnull
    Optional<E> findById(@Nonnull UUID id);

    @Nonnull
    Page<E> findAll(@Nonnull Pageable pageable);

}
