package transaction.manager.config;

import static transaction.manager.resource.util.ApplicationPathConstants.API_ROOT;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath(API_ROOT)
public class JaxRsApplicationPathConfig extends Application {
}
