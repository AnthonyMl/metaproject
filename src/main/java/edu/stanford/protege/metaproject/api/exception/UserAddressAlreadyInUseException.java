package edu.stanford.protege.metaproject.api.exception;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public class UserAddressAlreadyInUseException extends ElementNotFoundException {

    public UserAddressAlreadyInUseException() {
        super();
    }

    public UserAddressAlreadyInUseException(String message) {
        super(message);
    }

    public UserAddressAlreadyInUseException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAddressAlreadyInUseException(Throwable cause) {
        super(cause);
    }

    protected UserAddressAlreadyInUseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
