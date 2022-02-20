package transaction.manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import transaction.manager.domain.entity.Customer;
import transaction.manager.domain.record.CustomerRecord;
import transaction.manager.exception.EntityNotFoundException;
import transaction.manager.mapper.resolver.DocumentTypeMapperResolver;

@Mapper(uses = { DocumentTypeMapperResolver.class })
public interface CustomerMapper {

    @Mapping(target = "documentType.id", ignore = true)
    @Mapping(target = "documentType.code", ignore = true)
    @Mapping(target = "documentType.description", ignore = true)
    Customer map(CustomerRecord customerRecord) throws EntityNotFoundException;

    CustomerRecord map(Customer customer);

}
