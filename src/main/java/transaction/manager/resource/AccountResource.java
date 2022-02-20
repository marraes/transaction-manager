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
import jakarta.inject.Inject;
import transaction.manager.domain.entity.Account;
import transaction.manager.domain.record.AccountRecord;
import transaction.manager.exception.EntityNotFoundException;
import transaction.manager.mapper.AccountMapper;
import transaction.manager.service.AccountService;

@Path(ROOT)
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
    public AccountRecord create(@PathParam(CUSTOMER_ID_PARAM) final UUID customerId, @Valid final AccountCreationRecord accountCreationRecord) {
        try {
            final Account account = accountService.createAccount(customerId, accountCreationRecord.accountNumber());

            return accountMapper.map(account);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @GET
    @Path(CUSTOMER_ID_ACCOUNT_ID_RESOURCE)
    public AccountRecord findAccount(@PathParam(CUSTOMER_ID_PARAM) final UUID customerId, @PathParam(ACCOUNT_ID_PARAM) final UUID accountId) {
        try {
            final Account account = accountService.findByCustomerAndAccount(customerId, accountId);

            return accountMapper.map(account);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @Introspected
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static record AccountCreationRecord(@Nonnull String accountNumber) {
    }

}
