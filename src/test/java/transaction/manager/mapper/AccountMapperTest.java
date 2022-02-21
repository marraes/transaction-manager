package transaction.manager.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import transaction.manager.domain.entity.Account;
import transaction.manager.domain.entity.Customer;
import transaction.manager.domain.record.AccountRecord;

class AccountMapperTest {

    @Test
    void givenAccount_whenMapToAccountRecord_thenMustMapCorrectly() {
        final UUID customerId = UUID.fromString("0c6ab710-0660-4028-9e23-a191c378b7b1");
        final UUID accountId = UUID.fromString("60e48c92-8440-47a0-8af4-d38d2503448f");
        final Customer customer = new Customer();
        customer.setId(customerId);
        final Account account = Account.builder()
                .id(accountId)
                .accountNumber("123456")
                .customer(customer)
                .build();
        final AccountMapper accountMapper = new AccountMapperImpl();

        final AccountRecord mappedAccountRecord = accountMapper.map(account);

        assertNotNull(mappedAccountRecord);
        assertEquals(accountId, mappedAccountRecord.id());
        assertEquals("123456", mappedAccountRecord.accountNumber());
    }

}
