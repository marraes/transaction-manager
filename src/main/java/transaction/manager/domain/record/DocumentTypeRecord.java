package transaction.manager.domain.record;

import java.util.UUID;
import javax.validation.constraints.Max;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;

@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "DocumentType", description = "Object that represent a document type")
public record DocumentTypeRecord(
        @Schema(name = "id", description = "Document type identifier")
        UUID id,

        @Schema(name = "code", description = "Code 2-digits")
        @Max(999) Integer code,

        @Schema(name = "description", description = "Description for DocumentType")
        String description
) {
}
