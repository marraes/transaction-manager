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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import transaction.manager.domain.record.TransactionRegisterRequestRecord;
import transaction.manager.exception.EntityNotFoundException;
import transaction.manager.resource.doc.openapi.TagNameConstants;
import transaction.manager.resource.doc.openapi.annotation.ApiBadRequestErrorResponse;
import transaction.manager.resource.doc.openapi.annotation.ApiNotFoundErrorResponse;
import transaction.manager.resource.doc.openapi.annotation.ApiUnexpectedErrorResponse;
import transaction.manager.service.TransactionService;

@Path(ROOT)
@ApiUnexpectedErrorResponse
@Tag(name = TagNameConstants.TRANSACTION)
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
    @Operation(
            summary = "Register a transaction",
            description = "Register a new transaction for a determined account",
            operationId = "registerTransaction"
    )
    @ApiResponse(responseCode = "200", description = "Transaction received successfully")
    @ApiBadRequestErrorResponse
    @ApiNotFoundErrorResponse
    public void registerTransaction(
            @Parameter(
                    name = ACCOUNT_ID_PARAM,
                    in = ParameterIn.PATH,
                    description = "Account identifier",
                    required = true, schema = @Schema(format = "uuid", type = "string")
            )
            @PathParam(ACCOUNT_ID_PARAM) @Nonnull final UUID accountId,

            @RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(name = "TransactionRegisterRequest", implementation = TransactionRegisterRequestRecord.class)
                    )
            )
            @Valid final TransactionRegisterRequestRecord transactionRecord
    ) {
        try {
            transactionService.registerTransaction(accountId, transactionRecord);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

}
