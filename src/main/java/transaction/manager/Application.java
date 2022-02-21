package transaction.manager;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import transaction.manager.resource.util.ApplicationPathConstants;

@OpenAPIDefinition(
        servers = @Server(
                url = ApplicationPathConstants.API_ROOT
        ),
        info = @Info(
                title = "Transaction Manager",
                version = "1.0"
        )
)
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
