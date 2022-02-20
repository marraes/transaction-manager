package transaction.manager.repository;

import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import transaction.manager.domain.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    @Nonnull
    Optional<Account> findByCustomerIdAndId(@NotNull UUID customerId, @NotNull UUID accountId);

}
