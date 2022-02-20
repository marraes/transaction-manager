package transaction.manager.mapper;

import org.mapstruct.Mapper;
import transaction.manager.domain.entity.Account;
import transaction.manager.domain.record.AccountRecord;

@Mapper
public interface AccountMapper {

    AccountRecord map(Account account);

}
