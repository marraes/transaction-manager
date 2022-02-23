package transaction.manager.resource.doc.openapi.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.ws.rs.core.MediaType;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import transaction.manager.domain.dto.ApiError;

@Documented
@Retention(RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD})
@ApiResponse(
        responseCode = "400",
        description = "Invalid arguments",
        content = @Content(
                mediaType = MediaType.APPLICATION_JSON,
                schema = @Schema(name = "ApiError", implementation = ApiError.class)
        )
)
public @interface ApiBadRequestErrorResponse {
}
