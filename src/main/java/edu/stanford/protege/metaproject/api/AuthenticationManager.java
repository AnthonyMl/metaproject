package edu.stanford.protege.metaproject.api;

import edu.stanford.protege.metaproject.api.exception.UserAddressAlreadyInUseException;
import edu.stanford.protege.metaproject.api.exception.UserAlreadyRegisteredException;
import edu.stanford.protege.metaproject.api.exception.UserNotRegisteredException;

import java.util.Set;

/**
 * Manager for user authentication; handles the (modification of the) registration of users in the server, and the verification
 * of credentials for accessing the server. The AuthenticationManager maintains a collection of user authentication details
 *
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public interface AuthenticationManager extends Manager {

    /**
     * Register a user in the user registry
     *
     * @param userId  User identifier
     * @param password  Plain text password
     * @throws UserAlreadyRegisteredException   User is already registered
     * @throws UserAddressAlreadyInUseException Email address is already in use by another user
     */
    void add(UserId userId, PlainPassword password) throws UserAlreadyRegisteredException, UserAddressAlreadyInUseException;

    /**
     * Remove user from the authentication registry (the user will not be able to login)
     *
     * @param userId  User identifier
     * @throws UserNotRegisteredException   User is not registered
     */
    void remove(UserId userId) throws UserNotRegisteredException;

    /**
     * Get all entries of user authentication details
     *
     * @return Set of user authentication details
     */
    Set<AuthenticationDetails> getAuthenticationDetails();

    /**
     * Get the authentication details for a user with the given identifier
     *
     * @param userId    User identifier
     * @return Authentication details
     * @throws UserNotRegisteredException   User is not registered
     */
    AuthenticationDetails getAuthenticationDetails(UserId userId) throws UserNotRegisteredException;

    /**
     * Verify whether the given user-password pair is a valid (registered) one
     *
     * @param userId    User identifier
     * @param password  Password
     * @return true if user and password are valid w.r.t. the authentication details, false otherwise
     * @throws UserNotRegisteredException   User is not registered
     */
    boolean hasValidCredentials(UserId userId, PlainPassword password) throws UserNotRegisteredException;

    /**
     * Change password of a specified user to the given password
     *
     * @param userId  User identifier
     * @param password  New password
     * @throws UserNotRegisteredException   User is not registered
     */
    void changePassword(UserId userId, PlainPassword password) throws UserNotRegisteredException;

    /**
     * Change password of the user to the given password, based on the specified authentication details
     *
     * @param userDetails   User authentication details
     * @param password  New password
     */
    void changePassword(AuthenticationDetails userDetails, PlainPassword password);

}
