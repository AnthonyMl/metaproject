package edu.stanford.protege.metaproject.api.exception;

/**
 * @author Rafael Gonçalves <br>
 * Center for Biomedical Informatics Research <br>
 * Stanford University
 */
public class UnknownProjectIdException extends UnknownPolicyObjectIdException {
    private static final long serialVersionUID = -1164559865154277077L;

    public UnknownProjectIdException() {
        super();
    }

    public UnknownProjectIdException(String message) {
        super(message);
    }

    public UnknownProjectIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownProjectIdException(Throwable cause) {
        super(cause);
    }

    protected UnknownProjectIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
