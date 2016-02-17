package edu.stanford.protege.metaproject.api;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public class MalformedInputException extends RuntimeException {
    private static final long serialVersionUID = -8858266941411669800L;

    public MalformedInputException() {
        super();
    }

    public MalformedInputException(String message) {
        super(message);
    }

    public MalformedInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedInputException(Throwable cause) {
        super(cause);
    }

    protected MalformedInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
