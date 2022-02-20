package transaction.manager.repository;

import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import transaction.manager.domain.entity.TransactionStatus;

@Repository
public interface TransactionStatusRepository extends JpaRepository<TransactionStatus, UUID> {

    @Nonnull
    Optional<TransactionStatus> findByCode(@NotNull Integer dbCode);

}
