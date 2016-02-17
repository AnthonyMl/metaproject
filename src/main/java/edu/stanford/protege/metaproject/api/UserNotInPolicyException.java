package edu.stanford.protege.metaproject.api;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public class UserNotInPolicyException extends PolicyException {
    private static final long serialVersionUID = 9103902109203939672L;

    public UserNotInPolicyException() {
        super();
    }

    public UserNotInPolicyException(String message) {
        super(message);
    }

    public UserNotInPolicyException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotInPolicyException(Throwable cause) {
        super(cause);
    }

    protected UserNotInPolicyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
