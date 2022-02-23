package transaction.manager.domain.dto;

import java.time.Instant;
import java.util.List;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Introspected
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "ApiError", description = "Objects that represent an error returned")
public class ApiError {

    @JsonIgnore
    private Status status;

    @JsonIgnore
    @Builder.Default
    private Instant errorInstant = Instant.now();

    @JsonProperty("error")
    private String message;

    @JsonProperty("errors")
    private List<String> messages;

    @JsonProperty("timestamp")
    public long getTimestamp() {
        return errorInstant.toEpochMilli();
    }
}
