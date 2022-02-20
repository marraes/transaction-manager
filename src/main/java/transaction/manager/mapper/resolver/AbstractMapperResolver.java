package transaction.manager.mapper.resolver;

import java.util.UUID;
import java.util.function.Supplier;

import io.micronaut.core.annotation.Indexed;
import org.apache.commons.lang3.ObjectUtils;
import transaction.manager.domain.entity.generic.EntityDomain;
import transaction.manager.exception.EntityNotFoundException;
import transaction.manager.service.generic.CrudService;

@Indexed(AbstractMapperResolver.class)
public class AbstractMapperResolver {

    protected <E extends EntityDomain> E resolveToEntitySafety(
            final CrudService<E> service,
            final Supplier<UUID> entityIdSupplier,
            final Object... safetyRequiredObjects
    ) throws EntityNotFoundException {
        return resolveToEntitySafety(service, entityIdSupplier, null, safetyRequiredObjects);
    }

    protected <E extends EntityDomain> E resolveToEntitySafety(
            final CrudService<E> service,
            final Supplier<UUID> entityIdSupplier,
            final Supplier<E> defaultEntitySupplier,
            final Object... safetyRequiredObjects
    ) throws EntityNotFoundException {
        if (ObjectUtils.allNotNull(safetyRequiredObjects, entityIdSupplier, entityIdSupplier.get())) {
            final UUID id = entityIdSupplier.get();
            return service.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Not found ".concat(id.toString())));
        }

        return defaultEntitySupplier != null ? defaultEntitySupplier.get() : null;
    }

}
