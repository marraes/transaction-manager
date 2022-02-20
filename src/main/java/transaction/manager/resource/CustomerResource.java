package transaction.manager.resource;

import static transaction.manager.resource.util.ApplicationPathConstants.CUSTOMER_RESOURCE;
import static transaction.manager.resource.util.ApplicationPathConstants.ROOT;

import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jakarta.inject.Inject;
import org.apache.commons.lang3.ObjectUtils;
import transaction.manager.domain.entity.Customer;
import transaction.manager.domain.record.CustomerRecord;
import transaction.manager.domain.record.DocumentTypeRecord;
import transaction.manager.exception.EntityNotFoundException;
import transaction.manager.mapper.CustomerMapper;
import transaction.manager.service.CustomerService;

@Path(ROOT)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

    private final CustomerMapper customerMapper;
    private final CustomerService customerService;

    @Inject
    public CustomerResource(final CustomerMapper customerMapper, final CustomerService customerService) {
        this.customerMapper = customerMapper;
        this.customerService = customerService;
    }

    @POST
    @Path(CUSTOMER_RESOURCE)
    public CustomerRecord create(@Valid final CustomerRecord customerRecord) {
        final DocumentTypeRecord documentTypeRecord = customerRecord.documentType();

        if (ObjectUtils.anyNull(documentTypeRecord, documentTypeRecord.id())) {
            throw new BadRequestException("Required document type");
        }

        try {
            Customer customerEntity = customerMapper.map(customerRecord);
            customerEntity = customerService.save(customerEntity);

            return customerMapper.map(customerEntity);
        } catch (final EntityNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

}
