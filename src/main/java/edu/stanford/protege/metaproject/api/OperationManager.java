package edu.stanford.protege.metaproject.api;

import edu.stanford.protege.metaproject.api.exception.OperationNotFoundException;

import java.util.Optional;
import java.util.Set;

/**
 * A manager for accessing, adding, removing or editing existing operations
 *
 * The operation manager follows the singleton pattern; only one instance of this manager may exist.
 *
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public interface OperationManager extends Manager {

    /**
     * Add operation(s)
     *
     * @param operation One or more operations
     */
    void add(Operation... operation);

    /**
     * Remove the specified operation(s)
     *
     * @param operation One or more operations
     */
    void remove(Operation... operation);

    /**
     * Create a new operation
     *
     * @param name  Operation name
     * @param description   Operation description
     * @param operationType Operation type
     * @param prerequisites Set of operation prerequisites
     * @return New operation instance
     */
    Operation create(String name, String description, OperationType operationType, Optional<Set<OperationPrerequisite>> prerequisites);

    /**
     * Get all known operations
     *
     * @return Set of operations
     */
    Set<Operation> getOperations();

    /**
     * A convenience method to fetch an operation or die trying (with an exception)
     *
     * @param operationId    Operation identifier
     * @return Operation instance
     * @throws OperationNotFoundException    Operation not found
     */
    Operation getOperation(OperationId operationId) throws OperationNotFoundException;

    /**
     * Change the name of the given operation
     *
     * @param operationId Operation identifier
     * @param operationName New operation name
     * @throws OperationNotFoundException    Operation not found
     */
    void changeName(OperationId operationId, Name operationName) throws OperationNotFoundException;

    /**
     * Change the description of a given operation
     *
     * @param operationId Operation identifier
     * @param operationDescription  New operation description
     * @throws OperationNotFoundException    Operation not found
     */
    void changeDescription(OperationId operationId, Description operationDescription) throws OperationNotFoundException;

    /**
     * Add one or more operation prerequisites to the specified operation
     *
     * @param operationId   Operation identifier
     * @param prerequisites  Operation prerequisite(s)
     * @throws OperationNotFoundException   Operation not found
     */
    void addPrerequisite(OperationId operationId, OperationPrerequisite... prerequisites) throws OperationNotFoundException;

    /**
     * Remove one or more operation prerequisites from the specified operation
     *
     * @param operationId   Operation identifier
     * @param prerequisites  Operation prerequisite(s)
     * @throws OperationNotFoundException   Operation not found
     */
    void removePrerequisite(OperationId operationId, OperationPrerequisite... prerequisites) throws OperationNotFoundException;

}
