package transaction.manager.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.AccessLevel;
import lombok.Getter;
import transaction.manager.domain.entity.Customer;
import transaction.manager.repository.CustomerRepository;
import transaction.manager.service.generic.AbstractCrudService;

@Singleton
public class CustomerService extends AbstractCrudService<Customer> {

    @Getter(value = AccessLevel.PROTECTED, onMethod_ = { @Override })
    private final CustomerRepository repository;

    @Inject
    public CustomerService(final CustomerRepository repository) {
        this.repository = repository;
    }

}
