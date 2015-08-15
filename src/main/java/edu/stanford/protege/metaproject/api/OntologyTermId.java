package edu.stanford.protege.metaproject.api;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public interface OntologyTermId extends Id {

    IdPrefix getPrefix();

    IdSuffix getSuffix();

}
