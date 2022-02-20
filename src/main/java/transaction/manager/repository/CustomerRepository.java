package transaction.manager.repository;

import java.util.UUID;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import transaction.manager.domain.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
