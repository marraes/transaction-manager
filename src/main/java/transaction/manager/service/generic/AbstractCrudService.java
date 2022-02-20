package transaction.manager.service.generic;

import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.transaction.Transactional;
import javax.validation.Valid;

import io.micronaut.core.annotation.Indexed;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import transaction.manager.domain.entity.generic.EntityDomain;

@Indexed(AbstractCrudService.class)
public abstract class AbstractCrudService<E extends EntityDomain> implements CrudService<E> {

    @Nonnull
    @Override
    @Transactional
    public E save(@Valid @Nonnull final E entity) {
        return getRepository().save(entity);
    }

    @Nonnull
    @Override
    @Transactional
    public Optional<E> findById(@Nonnull final UUID id) {
        return getRepository().findById(id);
    }

    @Nonnull
    @Override
    @Transactional
    public Page<E> findAll(@Nonnull final Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Nonnull
    protected abstract JpaRepository<E, UUID> getRepository();

}
