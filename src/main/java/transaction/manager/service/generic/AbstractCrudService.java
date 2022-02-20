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
import lombok.extern.slf4j.Slf4j;
import transaction.manager.domain.entity.generic.EntityDomain;

@Slf4j
@Indexed(AbstractCrudService.class)
public abstract class AbstractCrudService<E extends EntityDomain> implements CrudService<E> {

    @Nonnull
    @Override
    @Transactional
    public E save(@Valid @Nonnull final E entity) {
        log.trace("save repositoryType={}", getRepository().getClass().getName());
        return getRepository().save(entity);
    }

    @Nonnull
    @Override
    @Transactional
    public Optional<E> findById(@Nonnull final UUID id) {
        log.trace("findById repositoryType={} id={}", getRepository().getClass().getName(), id);
        return getRepository().findById(id);
    }

    @Nonnull
    @Override
    @Transactional
    public Page<E> findAll(@Nonnull final Pageable pageable) {
        log.trace("findById repositoryType={} page={} offset={}", getRepository().getClass().getName(), pageable.getNumber(), pageable.getOffset());
        return getRepository().findAll(pageable);
    }

    @Nonnull
    protected abstract JpaRepository<E, UUID> getRepository();

}
