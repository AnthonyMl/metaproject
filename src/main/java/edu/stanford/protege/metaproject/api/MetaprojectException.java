package edu.stanford.protege.metaproject.api;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public class MetaprojectException extends Exception {
    private static final long serialVersionUID = 6615126169407057471L;

    public MetaprojectException() {
        super();
    }

    public MetaprojectException(String message) {
        super(message);
    }

    public MetaprojectException(String message, Throwable cause) {
        super(message, cause);
    }

    public MetaprojectException(Throwable cause) {
        super(cause);
    }

    protected MetaprojectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
