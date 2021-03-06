package edu.stanford.protege.metaproject.impl;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import edu.stanford.protege.metaproject.ConfigurationManager;
import edu.stanford.protege.metaproject.api.*;
import org.apache.commons.codec.binary.Hex;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A password hash generator based on PBKDF2 (Password-Based Key Derivation Function 2)
 *
 * @author Rafael Gonçalves <br>
 * Center for Biomedical Informatics Research <br>
 * Stanford University
 */
@Immutable
@ThreadSafe
public final class Pbkdf2PasswordHasher implements PasswordHasher {
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
    private final int hashByteSize, nrIterations;

    /**
     * Constructor
     *
     * @param hashByteSize  Hash byte size
     * @param nrIterations    Number of iterations
     */
    public Pbkdf2PasswordHasher(int hashByteSize, int nrIterations) {
        this.hashByteSize = checkNotNull(hashByteSize);
        this.nrIterations = checkNotNull(nrIterations);
    }

    @Override
    @Nonnull
    public SaltedPasswordDigest hash(@Nonnull PlainPassword password, @Nonnull Salt salt) {
        byte[] saltBytes = salt.getBytes();
        byte[] hash = hash(password.getPassword(), saltBytes, nrIterations, hashByteSize);
        return ConfigurationManager.getFactory().getSaltedPasswordDigest(Hex.encodeHexString(hash), salt);
    }

    private byte[] hash(String password, byte[] salt, int iterations, int bytes) {
        byte[] output = null;
        try {
            output = hash(password.toCharArray(), salt, iterations, bytes);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return output;
    }

    /**
     * Computes the PBKDF2 hash of a password
     *
     * @param password  Password to hash
     * @param salt  Salt
     * @param iterations    Iteration count (slowness factor)
     * @param bytes Length of the hash to compute
     * @throws NoSuchAlgorithmException Cryptographic algorithm not available in this environment
     * @throws InvalidKeySpecException  Invalid key specification
     */
    private byte[] hash(char[] password, byte[] salt, int iterations, int bytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
        return skf.generateSecret(spec).getEncoded();
    }

    public int getHashByteSize() {
        return hashByteSize;
    }

    public int getNumberOfIterations() {
        return nrIterations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pbkdf2PasswordHasher)) {
            return false;
        }
        Pbkdf2PasswordHasher that = (Pbkdf2PasswordHasher) o;
        return Objects.equal(hashByteSize, that.hashByteSize) &&
                Objects.equal(nrIterations, that.nrIterations);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(hashByteSize, nrIterations);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("hashByteSize", hashByteSize)
                .add("nrIterations", nrIterations)
                .toString();
    }
}
