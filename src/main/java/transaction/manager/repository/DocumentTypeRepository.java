package transaction.manager.repository;

import java.util.UUID;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import transaction.manager.domain.entity.DocumentType;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, UUID> {
}
