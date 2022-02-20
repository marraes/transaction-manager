package transaction.manager.exception;

import java.io.Serial;

public class EntityNotFoundException extends Exception {

    @Serial
    private static final long serialVersionUID = -2852447752472473321L;

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }


}
