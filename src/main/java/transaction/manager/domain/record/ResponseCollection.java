package transaction.manager.domain.record;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude
@Introspected
public record ResponseCollection<T>(
        @Nonnull @ArraySchema(schema = @Schema(allOf = { DocumentTypeRecord.class })) List<T> data,
        @Nonnull Long total
) {

    public static <T> ResponseCollection<T> of(final List<T> dataList, final Long total) {
        final List<T> dataListNullSafe = dataList != null ? dataList : Collections.emptyList();
        return new ResponseCollection<>(
                dataListNullSafe,
                total != null ? total : dataListNullSafe.size()
        );
    }

}
