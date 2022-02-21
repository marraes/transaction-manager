package transaction.manager.resource;

import static transaction.manager.resource.util.ApplicationPathConstants.DOCUMENT_TYPE_RESOURCE;
import static transaction.manager.resource.util.ApplicationPathConstants.ROOT;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Sort;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import transaction.manager.domain.entity.DocumentType;
import transaction.manager.domain.entity.DocumentType_;
import transaction.manager.domain.record.DocumentTypeRecord;
import transaction.manager.domain.record.ResponseCollection;
import transaction.manager.mapper.DocumentTypeMapper;
import transaction.manager.service.DocumentTypeService;

@Slf4j
@Path(ROOT)
@Tag(name = "Document Types")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DocumentTypeResource {

    private static final Pageable UNPAGED_BY_CODE = Pageable.UNPAGED.order(DocumentType_.CODE, Sort.Order.Direction.ASC);

    private final DocumentTypeService documentTypeService;
    private final DocumentTypeMapper documentTypeMapper;

    @Inject
    public DocumentTypeResource(final DocumentTypeService documentTypeService, final DocumentTypeMapper documentTypeMapper) {
        this.documentTypeService = documentTypeService;
        this.documentTypeMapper = documentTypeMapper;
    }

    @GET
    @Path(DOCUMENT_TYPE_RESOURCE)
    public ResponseCollection<DocumentTypeRecord> list() {
        log.info("test");
        final Page<DocumentType> documentTypePage = documentTypeService.findAll(UNPAGED_BY_CODE);
        final List<DocumentType> documentTypes = documentTypePage.getContent();
        final List<DocumentTypeRecord> documentTypeRecords = documentTypeMapper.map(documentTypes);

        return ResponseCollection.of(documentTypeRecords, documentTypePage.getTotalSize());
    }

}
