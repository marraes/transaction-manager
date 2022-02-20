package transaction.manager.domain.entity.generic;

import java.io.Serializable;
import java.util.UUID;

public interface EntityDomain extends Serializable {

    UUID getId();

}
