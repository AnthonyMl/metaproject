package edu.stanford.protege.metaproject.api;

import java.util.Set;

/**
 * A role defines a set of allowed operations. It essentially represents a permission set
 * that can be reused for different users in different projects.
 *
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public interface Role extends MetaprojectObject<RoleId>, HasDescription, Comparable<Role> {

    /**
     * Get the set of operations associated with this role
     *
     * @return Set of operations identifiers
     */
    Set<OperationId> getOperations();

}
