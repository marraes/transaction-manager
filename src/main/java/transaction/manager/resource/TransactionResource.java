package transaction.manager.resource;

import static transaction.manager.resource.util.ApplicationPathConstants.ACCOUNT_ID_PARAM;
import static transaction.manager.resource.util.ApplicationPathConstants.CUSTOMER_ACCOUNT_ID_TRANSACTION_RESOURCE;
import static transaction.manager.resource.util.ApplicationPathConstants.ROOT;

import java.util.UUID;
import javax.annotation.Nonnull;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import transaction.manager.domain.record.TransactionRegisterRequestRecord;
import transaction.manager.exception.EntityNotFoundException;
import transaction.manager.service.TransactionService;

@Path(ROOT)
@Tag(name = "Transactions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransactionResource {

    private final TransactionService transactionService;

    @Inject
    public TransactionResource(final TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @POST
    @Path(CUSTOMER_ACCOUNT_ID_TRANSACTION_RESOURCE)
    public void registerTransaction(@PathParam(ACCOUNT_ID_PARAM) @Nonnull final UUID accountId, @Valid final TransactionRegisterRequestRecord transactionRecord) {
        try {
            transactionService.registerTransaction(accountId, transactionRecord);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

}
