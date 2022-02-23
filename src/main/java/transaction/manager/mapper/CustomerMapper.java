package transaction.manager.mapper;

import transaction.manager.domain.entity.Customer;
import transaction.manager.domain.record.CustomerRecord;
import transaction.manager.exception.EntityNotFoundException;

//@Mapper(uses = { DocumentTypeMapper.class, DocumentTypeMapperResolver.class })
public interface CustomerMapper {

    //    @Mapping(target = "documentType.id", ignore = true)
//    @Mapping(target = "documentType.code", ignore = true)
//    @Mapping(target = "documentType.description", ignore = true)
    Customer map(CustomerRecord customerRecord) throws EntityNotFoundException;

    CustomerRecord map(Customer customer);

}
