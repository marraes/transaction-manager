package transaction.manager.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import transaction.manager.domain.entity.DocumentType;
import transaction.manager.domain.record.DocumentTypeRecord;

@Mapper
public interface DocumentTypeMapper {

    List<DocumentTypeRecord> map(List<DocumentType> documentTypes);

}
