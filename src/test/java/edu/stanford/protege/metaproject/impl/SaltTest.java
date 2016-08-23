package edu.stanford.protege.metaproject.impl;

import edu.stanford.protege.metaproject.TestUtils;
import edu.stanford.protege.metaproject.api.Salt;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Rafael Gonçalves <br>
 * Center for Biomedical Informatics Research <br>
 * Stanford University
 */
public class SaltTest {
    private static final String
            saltStr = "testSalt1",
            diffSaltStr = "testSalt2",
            toStringHead = Salt.class.getSimpleName();

    private Salt salt, otherSalt, diffSalt;

    @Before
    public void setUp() {
        salt = TestUtils.getSalt(saltStr);
        otherSalt = TestUtils.getSalt(saltStr);
        diffSalt = TestUtils.getSalt(diffSaltStr);
    }

    @Test
    public void testNotNull() {
        assertThat(salt, is(not(equalTo(null))));
    }

    @Test
    public void testGetString() {
        assertThat(salt.getString(), is(saltStr));
    }

    @Test
    public void testGetBytes() {
        assertThat(salt.getBytes(), is(saltStr.getBytes()));
    }

    @Test
    public void testEqualToSelf() {
        assertThat(salt, is(salt));
    }

    @Test
    public void testEquals() {
        assertThat(salt, is(otherSalt));
    }

    @Test
    public void testNotEquals() {
        assertThat(salt, is(not(diffSalt)));
    }

    @Test
    public void testHashCode() {
        assertThat(salt.hashCode(), is(otherSalt.hashCode()));
    }

    @Test
    public void testToString() {
        assertThat(salt.toString(), startsWith(toStringHead));
    }
}