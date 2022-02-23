package transaction.manager.exception;

public class TransactionValidationException extends Exception {

    public TransactionValidationException() {
    }

    public TransactionValidationException(String message) {
        super(message);
    }

    public TransactionValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionValidationException(Throwable cause) {
        super(cause);
    }

    public TransactionValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
