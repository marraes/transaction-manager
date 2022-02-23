package transaction.manager.resource;

import static transaction.manager.resource.util.ApplicationPathConstants.ACCOUNT_ID_PARAM;
import static transaction.manager.resource.util.ApplicationPathConstants.CUSTOMER_ID_ACCOUNT_ID_RESOURCE;
import static transaction.manager.resource.util.ApplicationPathConstants.CUSTOMER_ID_ACCOUNT_RESOURCE;
import static transaction.manager.resource.util.ApplicationPathConstants.CUSTOMER_ID_PARAM;
import static transaction.manager.resource.util.ApplicationPathConstants.ROOT;

import java.util.UUID;
import javax.annotation.Nonnull;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import transaction.manager.domain.entity.Account;
import transaction.manager.domain.record.AccountRecord;
import transaction.manager.exception.EntityNotFoundException;
import transaction.manager.mapper.AccountMapper;
import transaction.manager.resource.doc.openapi.TagNameConstants;
import transaction.manager.resource.doc.openapi.annotation.ApiBadRequestErrorResponse;
import transaction.manager.resource.doc.openapi.annotation.ApiNotFoundErrorResponse;
import transaction.manager.resource.doc.openapi.annotation.ApiUnexpectedErrorResponse;
import transaction.manager.service.AccountService;

@Path(ROOT)
@ApiBadRequestErrorResponse
@ApiUnexpectedErrorResponse
@Tag(name = TagNameConstants.ACCOUNT)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    private final AccountMapper accountMapper;
    private final AccountService accountService;

    @Inject
    public AccountResource(final AccountMapper accountMapper, final AccountService accountService) {
        this.accountMapper = accountMapper;
        this.accountService = accountService;
    }

    @POST
    @Path(CUSTOMER_ID_ACCOUNT_RESOURCE)
    @Operation(
            summary = "Create a new account",
            description = "Create a new account for a customer",
            operationId = "createAccount"
    )
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(name = "Account", implementation = AccountRecord.class)))
    @ApiNotFoundErrorResponse
    public AccountRecord create(
            @Parameter(
                    name = CUSTOMER_ID_PARAM,
                    in = ParameterIn.PATH,
                    description = "Customer identifier",
                    required = true, schema = @Schema(format = "uuid", type = "string")
            )
            @PathParam(CUSTOMER_ID_PARAM) final UUID customerId,

            @RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(name = "AccountRequest", implementation = AccountCreationRecord.class)
                    )
            )
            @Valid final AccountCreationRecord accountCreationRecord
    ) {
        try {
            final Account account = accountService.createAccount(customerId, accountCreationRecord.accountNumber());

            return accountMapper.map(account);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @GET
    @Path(CUSTOMER_ID_ACCOUNT_ID_RESOURCE)
    @Operation(
            summary = "Find an account",
            description = "Return an account that exists",
            operationId = "findAccount"
    )
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(name = "Account", implementation = AccountRecord.class)))
    @ApiNotFoundErrorResponse
    public AccountRecord findAccount(
            @Parameter(
                    name = CUSTOMER_ID_PARAM,
                    in = ParameterIn.PATH,
                    description = "Customer identifier",
                    required = true, schema = @Schema(format = "uuid", type = "string")
            )
            @PathParam(CUSTOMER_ID_PARAM) final UUID customerId,

            @Parameter(
                    name = ACCOUNT_ID_PARAM,
                    in = ParameterIn.PATH,
                    description = "Account identifier",
                    required = true, schema = @Schema(format = "uuid", type = "string")
            )
            @PathParam(ACCOUNT_ID_PARAM) final UUID accountId
    ) {
        try {
            final Account account = accountService.findByCustomerAndAccount(customerId, accountId);

            return accountMapper.map(account);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @Introspected
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(name = "AccountRequest", description = "Object with args to create a new account")
    public static record AccountCreationRecord(@Nonnull String accountNumber) {
    }

}
