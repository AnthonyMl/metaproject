package edu.stanford.protege.metaproject.api;

/**
 * A representation of a hashed password, consisting of the password hash,
 * and the salt used to hash the password
 *
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public interface SaltedPasswordDigest extends Password {

    /**
     * Get the salt used in for hashing this password
     *
     * @return Salt used in hashing function
     */
    Salt getSalt();

}
