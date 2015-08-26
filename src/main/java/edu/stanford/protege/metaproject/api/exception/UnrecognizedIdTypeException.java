package edu.stanford.protege.metaproject.api.exception;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public class UnrecognizedIdTypeException extends MetaprojectException {
    private static final long serialVersionUID = -913492734865078766L;

    public UnrecognizedIdTypeException() {
        super();
    }

    public UnrecognizedIdTypeException(String message) {
        super(message);
    }

    public UnrecognizedIdTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnrecognizedIdTypeException(Throwable cause) {
        super(cause);
    }

    protected UnrecognizedIdTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
