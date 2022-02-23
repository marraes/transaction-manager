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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import org.apache.commons.lang3.ObjectUtils;
import transaction.manager.domain.entity.Customer;
import transaction.manager.domain.record.CustomerRecord;
import transaction.manager.domain.record.DocumentTypeRecord;
import transaction.manager.exception.EntityNotFoundException;
import transaction.manager.mapper.CustomerMapper;
import transaction.manager.resource.doc.openapi.TagNameConstants;
import transaction.manager.resource.doc.openapi.annotation.ApiBadRequestErrorResponse;
import transaction.manager.resource.doc.openapi.annotation.ApiNotFoundErrorResponse;
import transaction.manager.resource.doc.openapi.annotation.ApiUnexpectedErrorResponse;
import transaction.manager.service.CustomerService;

@Path(ROOT)
@ApiUnexpectedErrorResponse
@Tag(name = TagNameConstants.CUSTOMER)
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
    @Operation(
            summary = "Create a new customer",
            description = "Create a new account",
            operationId = "createCustomer"
    )
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(name = "Customer", implementation = CustomerRecord.class)))
    @ApiBadRequestErrorResponse
    @ApiNotFoundErrorResponse
    public CustomerRecord create(
            @RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(name = "Customer", implementation = CustomerRecord.class)
                    )
            )
            @Valid final CustomerRecord customerRecord
    ) {
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
